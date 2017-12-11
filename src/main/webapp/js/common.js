var pageUtil = {
    setPageSize: function (field, formId) {
        $("#" + formId + "_pageSize").attr("value", field.value);
        $("#" + formId + "_page").attr("value", 1);
        $("#" + formId).submit();
    },

    setPage: function (index, formId) {
        $("#" + formId + "_page").attr("value", index);
        $("#" + formId).submit();
    },

    submit: function (formId) {
        $("#" + formId + "_page").attr("value", 1);
        $("#" + formId).submit();
    }
}
