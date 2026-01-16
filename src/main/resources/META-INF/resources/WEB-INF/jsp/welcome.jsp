<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <h1>Welcome ${username}</h1>
    <a href="list-todos">Manage</a> your todos

    <hr>
    <h3>AJAX Test</h3>
    <button id="ajaxButton" class="btn btn-primary">Start Timer</button>
    <div id="ajaxResult" class="mt-3"></div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const button = document.getElementById("ajaxButton");
        const resultDiv = document.getElementById("ajaxResult");

        button.addEventListener("click", function() {
            resultDiv.innerHTML = "Waiting for response...";

            fetch("ajax-timer")
                .then(response => response.text())
                .then(data => {
                    resultDiv.innerHTML = data;
                })
                .catch(error => {
                    resultDiv.innerHTML = "Error: " + error;
                });
        });
    });
</script>

<%@ include file="common/footer.jspf" %>