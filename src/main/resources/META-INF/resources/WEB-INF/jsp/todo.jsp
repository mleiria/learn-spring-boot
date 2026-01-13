<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <h1>Enter Todo details</h1>
    <form:form method="post" modelAttribute="todo">

        <fieldset class="mb-3">
            <form:label path="description">Description:</form:label>
            <form:input type="text" id="description" name="description" required="required" path="description"/>
            <form:errors path="description" cssClass="text-warning"/>
        </fieldset>
        <fieldset class="mb-3">
            <form:label path="targetDate">Target Date:</form:label>
            <form:input type="text" id="targetDate" name="targetDate" required="required" path="targetDate"/>
            <form:errors path="targetDate" cssClass="text-warning"/>
        </fieldset>

        <form:input type="hidden" id="id" name="id" path="id"/>
        <form:input type="hidden" id="done" name="done" path="done"/>
        <input type="submit" value="Submit" class="btn btn-success">
    </form:form>


</div>

<%@ include file="common/footer.jspf" %>

<script type="text/javascript">
    $('#targetDate').datepicker({
        format: 'yyyy-mm-dd'
    });
</script>