<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <h1>Real-Time Coordinates</h1>
    <p>Received Coordinates: <span id="coordinates-display"></span></p>
</div>

<script type="text/javascript">
    var stompClient = null;

    function connect() {
        var socket = new SockJS('/gs-guide-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/coordinates', function (update) {
                showCoordinates(JSON.parse(update.body).content);
            });
        });
    }

    function disconnect() {
        if (stompClient !== null) {
            stompClient.disconnect();
        }
        console.log("Disconnected");
    }

    function showCoordinates(message) {
        $("#coordinates-display").text(message);
    }

    $(document).ready(function() {
        connect();
    });

    $(window).on('beforeunload', function() {
        disconnect();
    });
</script>

<%@ include file="common/footer.jspf" %>
