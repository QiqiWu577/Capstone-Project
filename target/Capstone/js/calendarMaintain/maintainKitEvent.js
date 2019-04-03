$(document).ready(function() {

    //------ start to perform adding shift function
    var newShiftDialog,newShiftForm,startDate,color,
        tips = $(".validateTips"),
        fname = $("#fname"),
        lname = $("#lname"),
        startTime = $("#start"),
        endTime = $("#end"),
        allFields = $([]).add(fname).add(lname).add(startTime).add(endTime);

    function updateTips(t) {
        tips
            .text(t)
            .addClass("ui-state-highlight");

        setTimeout(function () {
            tips.removeClass("ui-state-highlight", 1500);
        },500);
    }

    function checkEmp(o){

        if(o.val().length < 1){
            o.addClass("ui-state-error");
            updateTips("You must enter the employee's name!");
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
        var st = moment(s.val(), 'h:mma');
        var et = moment(e.val(), 'h:mma');
        var check = moment(st).isBefore(et);
        if(!check){
            updateTips("Opening hour must be earlier than the closing hour!");
            return false;
        }
        return true;
    }

    function setColor(s,e){

    }

    function addShift(){
        var valid = true;
        allFields.removeClass( "ui-state-error" );

        valid = valid && checkEmp(fname);
        valid = valid && checkEmp(lname);
        valid = valid && checkNotEmpty(startTime,0);
        valid = valid && checkNotEmpty(endTime,1);
        valid = valid && checkTime(startTime,endTime);

        if(valid){

            var start = startDate+"T"+startTime.val();
            var end = startDate+"T"+endTime.val();
            var name = fname.val()+" "+lname.val();

            var shift = {
                id: "add",
                title: name,
                start: start,
                end: end,
                color: "D"
            };

            //save to the database
            saveEvent(shift);

            newShiftDialog.dialog( "close" );
        }
        console.log(valid);
        return valid;
    }

    newShiftDialog = $("#newShiftDialog").dialog({
        autoOpen: false,
        height: 400,
        width: 350,
        modal: true,
        buttons: {
            "Create": addShift,
            Cancel: function(){
                newShiftDialog.dialog("close");
            }
        },
        close: function () {
            newShiftForm[0].reset();
            allFields.removeClass("ui-state-error");
        }
    });

    newShiftForm = newShiftDialog.find("form").on("submit",function (event) {
        event.preventDefault();
        addShift();
    });
    //------ the end of performing adding shift function


    //------ start to perform deleting shift function
    var deleteShiftDialog,deleteShiftForm,id;

    function deleteShift(){
        console.log(true);
        var data = {
            id: id,
            title: "delete",
            start: ds,
            end: de,
            color: "",
            type:"K"
        };

        $.ajax({
            type: "POST",
            url: 'EditCalendar',
            contentType: "application/json",
            data: JSON.stringify(data), //pass data to the servlet
            success: function(data){    //get data from the servlet
                if(data === 'success'){
                    alert("Deleting the shift is successful!");
                    check = true;
                    setTimeout("location.reload(true);",1000);
                }else if(data === 'fail'){
                    alert("Fail to delete the shift.");
                    setTimeout("location.reload(true);",1000);
                }
            }
        });

        return true;
    }

    deleteShiftDialog = $( "#deleteShiftDialog" ).dialog({
        autoOpen: false,
        resizable: false,
        height: "auto",
        width: 400,
        modal: true,
        buttons: {
            "Delete": function() {
                $( this ).dialog( "close" );
                deleteShift();
            },
            Cancel: function() {
                $( this ).dialog( "close" );
            }
        }
    });

    //------ the end of performing deleting shift function


    //save shift after editing
    function saveEvent(event){
        var check = false;

        var data = {
            id: event.id,
            title: event.title,
            start: event.start,
            end: event.end,
            color: event.color,
            type: 'K'
        };

        $.ajax({
            type: "POST",
            url: 'EditCalendar',
            contentType: "application/json",
            data: JSON.stringify(data), //pass data to the servlet
            success: function(data){    //get data from the servlet
                if(data === 'crossover'){
                    alert("The shifts of the same employee cross over! Please select the different shift time for the employee!");

                    setTimeout("location.reload(true);",1000);
                }else if(data === 'noEmp'){
                    alert("There is no such employee existing in this department!");
                }else if(data === 'success'){
                    alert("Adding new shift is successful!");
                    check = true;
                    setTimeout("location.reload(true);",1000);
                }else{
                    check = true;
                    setTimeout("location.reload(true);",1000);
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
        events:  'ShowKitCalendar',

        select: function(start,end,jsEvent,view){

            startDate = moment(start).format("YYYY-MM-DD");

            $("#newShiftDialog").dialog("open");

            $('#calendar').fullCalendar('unselect');
        },

        eventClick: function(event, element) {

            //pop up dialog
            //if user does want it, use method "$('#calendar').fullCalendar('removeEvents', event);"
            //if need to refresh the page, use "$('#calendar').fullCalendar('rederEvent');"
            id = event.id;
            ds = event.start;
            de = event.end;
            $("#deleteShiftDialog").dialog("open");
            $('#calendar').fullCalendar('unselect');
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


