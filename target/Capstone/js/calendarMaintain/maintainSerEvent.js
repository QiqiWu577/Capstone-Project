$(document).ready(function() {

    //------ start to perform adding shift function
    var dialog,form,startDate,color,
        tips = $(".validateTips"),
        employee = $("#employee"),
        startTime = $("#start"),
        endTime = $("#end"),
        allFields = $([]).add(employee).add(startTime).add(endTime);

    function updateTips(t) {
        tips
            .text(t)
            .addClass("ui-state-highlight");

        setTimeout(function () {
            tips.removeClass("ui-state-highlight", 1500);
        },500);
    }

    function checkEmp(o){

        if(o.val().length > 16 || o.val().length < 3){
            o.addClass("ui-state-error");
            updateTips("Length of employee's name must be between 3 and 16.");
            return false;
        }else{
            return true;
        }
    }

    function checkNotEmpty(o,n){

        var time = o.val();
        if(n==0){
            if(time === "" || time === null){
                o.addClass("ui-state-error");
                updateTips("Please select the start time for employee.");
                return false;
            }else {
                return true;
            }
        }else if(n==1){
            if(time === "" || time === null){
                o.addClass("ui-state-error");
                updateTips("Please select the end time for employee.");
                return false;
            }else {
                return true;
            }
        }

    }

    function checkTime(s,e){
        var check = moment(s.val()).isBefore(e.val());
        if(!check){
            updateTips("Opening hour must be earlier than the closing hour!");
        }
    }

    function setColor(s,e){

    }

    function addShift(){
        var valid = true;
        allFields.removeClass( "ui-state-error" );

        valid = valid && checkEmp(employee);
        valid = valid && checkNotEmpty(startTime,0);
        valid = valid && checkNotEmpty(endTime,1);
        valid = valid && checkTime(startTime,endTime);

        if(valid){

            var start = startDate+" "+startTime.val();
            var end = startDate+" "+endTime.val();

            //update shift in the calendar
            $('#calendar').fullCalendar('renderEvent',{
                title: employee.val(),
                start: start,
                end: end
            });

            var shift = {
                id: "add",
                title: employee.val(),
                start: start,
                end: end,
                color: "D"
            };

            //save to the database
            saveEvent(shift);

            dialog.dialog( "close" );
        }
        return valid;
    }

    dialog = $("#dialog").dialog({
        autoOpen: false,
        height: 400,
        width: 350,
        modal: true,
        buttons: {
            "Create": addShift,
            Cancel: function(){
                dialog.dialog("close");
            }
        },
        close: function () {
            form[0].reset();
            allFields.removeClass("ui-state-error");
        }
    });

    form = dialog.find("form").on("submit",function (event) {
        event.preventDefault();
        addShift();
    });
    //------ the end of performing adding shift function

    //save shift after editing
    function saveEvent(event){

        var data = {
            id: event.id,
            title: event.title,
            start: event.start,
            end: event.end,
            color: event.color
        };

        $.ajax({
            type: "POST",
            url: 'EditSerCalendar',
            contentType: "application/json",
            data: JSON.stringify(data), //pass data to the servlet
            success: function(data){    //get data from the servlet
                if(data === 'sameEmpShift') {
                    alert("Cannot be the same shift! Please select the different shift time for the employee!");
                }else if(data === 'crossover'){
                    alert("The shifts of the same employee cross over! Please select the different shift time for the employee!");
                }
            }
        });

    }

    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        height: 550,
        contentHeight: 'auto',
        handleWindowResize: true,
        navLinks: true,
        editable: true,
        eventLimit: true,
        themeSystem: 'jquery-ui',
        selectable: true,
        allDaySlot: false,
        events:  'ShowSerCalendar',

        select: function(start,end,jsEvent,view){

            startDate = moment(start).format("YYYY-MM-DD");

            $("#dialog").dialog("open");

            $('#calendar').fullCalendar('unselect');
        },

        eventClick: function(event, element) {

            //pop up dialog
            //if user does want it, use method "$('#calendar').fullCalendar('removeEvents', event);"
            //if need to refresh the page, use "$('#calendar').fullCalendar('rederEvent');"

            //save(event);

        },

        eventDrop: function(event, delta, revertFunc) {

            saveEvent(event);

        },

        eventResize: function(event, delta, revertFunc) {

            saveEvent(event);

        }

    });
});


