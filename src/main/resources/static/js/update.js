// (1) 회원정보 수정
function update(userId, event) {
    event.preventDefault();
    let data =$("#profileUpdate").serialize();
    console.log(data)
    $.ajax({
        type:  "put",
        url: `/api/user/${userId}`,
        data:data,
        contentType: "application/x-www-form-urlencoded"
    }).done(res=>{//HttpStatus 200번대
        console.log("update 성공")
        location.href =`/user/${userId}`
    }).fail(error =>{// HTTPstatus 200을 제외한 상태코드
        if (error.data == null) {
            alert(error.responseJSON.message)
        }else{
            alert(JSON.stringify(error.responseJSON.data))
        }
    })
}