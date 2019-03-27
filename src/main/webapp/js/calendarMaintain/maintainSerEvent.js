$(document).ready(function() {
    //------ start to perform adding shift function
    var dialog,form,startDate,endDate,
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
        //console.log("test"+o.val());
        if(o.val().length > 16 || o.val().length < 3){
            o.addClass("ui-state-error");
            updateTips("Length of employee's name must be between 3 and 16.");
            return false;
        }else{
            return true;
        }
    }

    function checkTimeFormat(time,error){
        var regex = RegExp('(([0-1][0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]');
        if(!(regex.test(time.val()))){
            time.addClass("ui-state-error");
            updateTips(error);
            return false;
        }else{
            return true;
        }
    }

    function addShift(){
        var valid = true;
        allFields.removeClass( "ui-state-error" );

        valid = valid && checkEmp(employee);
        valid = valid && checkTimeFormat(startTime,"Please use the format: hh:mm:ss");
        valid = valid && checkTimeFormat(endTime,"Please use the format: hh:mm:ss");

        if(valid){

            if(startDate != null || endDate != null){

                var startf = moment(startDate+"T"+startTime.val());
                var endf = moment(endDate+"T"+endTime.val());

                //update shift in the calendar
                $('#calendar').fullCalendar('renderEvent',{
                    title: employee,
                    start: startf,
                    end: endf
                });

                var shift = {
                    id: "add",
                    title: employee,
                    start: startf,
                    end: endf,
                    color: "null"
                };

                //save to the database
                saveEvent(shift);
                dialog.dialog( "close" );
            }
        }
        return valid;
    }

    dialog = $("#dialog").dialog({
        autoOpen: false,
        height: 400,
        width: 350,
        modal: true,
        position: ["middle",30],
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
                if(data === 'sameEmp'){
                    alert("test!");
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

            console.log(start+" "+end);
            startDate = start;
            endDate = end;

            $("#dialog").dialog("open");

            $('#calendar').fullCalendar('unselect');
        },

        eventClick: function(event, element) {

            $('#calendar').fullCalendar('updateEvent', event);

        },

        eventDrop: function(event, delta, revertFunc) {

            saveEvent(event);

        },

        eventResize: function(event, delta, revertFunc) {

            saveEvent(event);

        }

    });
});


