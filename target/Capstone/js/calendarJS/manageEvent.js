$(document).ready(function() {

    function saveEvent(event){

        var data = {
            title: event.title,
            start: event.start,
            end: event.end
        };

        $.ajax({
            type: "POST",
            url: 'EditCalendar',
            contentType: "application/json",
            data: JSON.stringify(data),
        });

    }

    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        navLinks: true,
        editable: true,
        eventLimit: true,
        themeSystem: 'jquery-ui',
        selectable: true,
        allDaySlot: false,

        events:  'ShowCalendar',

        select: function(start,end,jsEvent,view){

            var title = prompt('Event Title:');

            if(title){

                $('#calendar').fullCalendar('renderEvent',{
                   title: title,
                    start: start,
                    end: end
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


