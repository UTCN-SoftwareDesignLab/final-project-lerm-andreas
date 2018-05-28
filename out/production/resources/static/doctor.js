$(function() {
    'use strict';

    var client;

    function showMessage(mesg)
    {
        var date = mesg.date.split('T')[0];
        $('#messages').append('<tr>' +
            '<td>' + mesg.patientName + '</td>' +
            '<td>' + mesg.doctorName + '</td>' +
            '<td>' + date + '</td>' +
            '</tr>');
    }

    $(document).ready(function connect(){
        client = Stomp.over(new SockJS('/chat'));
        client.connect({}, function (frame) {
            alert();
            $("#conversation").show();
            client.subscribe('/topic/messages', function (message) {
                showMessage(JSON.parse(message.body));
            });
        });
    });
});