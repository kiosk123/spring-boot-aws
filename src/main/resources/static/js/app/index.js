const main = {
    init: function() {
        $("#btn-save").on('click', () => this.save())
        $("#btn-update").on('click', () => this.update())
    },

    save: function() {
        const data = {
            title: $("#title").val(),
            author: $("#author").val(),
            content: $("#content").val()
        }

        $.ajax({
            type: "POST",
            url: "/api/v1/posts",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 등록되었습니다')
            window.location.href = '/'
        }).fail(function(error) {
            alert(JSON.stringify(error))
        });
    },
    update: function() {
        const data = {
            title: $("#title").val(),
            content: $("#content").val()
        }

        const id = $("#id").val()

        $.ajax({
            type: "PUT",
            url: "/api/v1/posts/" + id,
            data: JSON.stringify(data),
            dataType: "json",
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 수정되었습니다')
            window.location.href = '/'
        }).fail(function(error) {
            alert(JSON.stringify(error))
        });
    }
}

main.init()