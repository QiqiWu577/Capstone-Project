$(document).ready(function() {

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
            contentType: "application/json",
            data: JSON.stringify(data)
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

            var title = prompt('Employee:');
            var s = prompt('Event Start Time(yyyy-mm-ddThh:mm:ss):');
            var e = prompt('Event End Time(yyyy-mm-ddThh:mm:ss):');

            if(title){

                $('#calendar').fullCalendar('renderEvent',{
                   title: title,
                    start: s,
                    end: e
                });
            }

            $('#calendar').fullCalendar('unselect');

            saveEvent({'title': title,'start': start,'end': end});

        },

        eventClick: function(event, element) {

            event.title = "CLICKED!";

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


