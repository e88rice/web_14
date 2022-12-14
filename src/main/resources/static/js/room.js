const request = new XMLHttpRequest();
const reservedListUl = document.getElementById("reserved-room-ul")
const emptyListUl = document.getElementById("empty-room-ul")
const insertBtn = document.getElementById("submitBtn");

window.onload = () => {
    get_all_rooms();
}

function get_all_rooms(){
    request.open('GET', "/room");
    request.send();
    request.onload = () => {
        if (request.status === 200){
            make_room_info(JSON.parse(request.response)); // 객체를 JSON 형태로 변환
        }
    }
}

// function insert_room(){
//     request.open('POST', '/room');
//     request.setRequestHeader('content-type', 'application/json');
//     request.send
//
//     request.onload = () => {
//         get_all_rooms();
//     }
// }


function make_room_info(roomsObj){
    for (room of roomsObj){
        let isEmptyText = room.reserveVO === null ? '비었음' : '예약중'; // 예약상태
        const roomNo = room.roomVO.roomNo+"호  ";                                  //방번호
        const roomImage =  "                <img src=\"/images/"+room.roomVO.roomNo+".jpg\" alt=\"방사진\" width=\"100\">";
        const roomPrice = room.roomVO.roomPrice+"원  ";                            // 방가격
        const roomSize = "최대 "+room.roomVO.roomSize+"인  ";                               // 방크기
        const liText = "                <div class='room-info-wrap'><li>"+roomNo+roomImage+roomPrice+roomSize+"</li>\n" +
            "                <button class=\"hide-btn\">숨기기</button>\n" +
            "                <button class=\"delete-btn\">X</button></div>";
        if (isEmptyText === '비었음'){
            emptyListUl.insertAdjacentHTML("beforeend", liText);
        }
        if (isEmptyText === '예약중'){
            reservedListUl.insertAdjacentHTML("beforeend", liText);
        }

    }
}