package Hotel;

/**
 * 酒店类，酒店中有多个房间供客人入驻
 * 提供入住，退房，打印所有房间状态功能
 */
public class Hotel {
    private Room[][] rooms;
    private int roomSize;

    public Hotel(int roomSize) {
        this.roomSize = roomSize;
        rooms = new Room[roomSize][roomSize];
        for(int i = 0; i < roomSize; i++)
            for(int j = 0; j < roomSize; j++){
                rooms[i][j] = new Room();
            }
    }


    public void checkIn(int floor, int no, int peoNum){
        if(!existRoom(floor, no)) {
            System.out.println("房间不存在，请输入正确的房间号");
            return;
        }
        Room thisRoom = rooms[floor][no];
        if(thisRoom.empty()){
            thisRoom.checkIn(peoNum);
            System.out.println(thisRoom);
            System.out.println("入住成功");
        }else{
            System.out.println("已有人入住，请选择其他房间");
        }
    }

    public void checkOut(int floor, int no){
        Room thisRoom = rooms[floor][no];
        if(existRoom(floor, no) && !thisRoom.empty()){
            System.out.println("退房成功");
            thisRoom.chekOut();
        }else System.out.println("退房失败，房间未入住或房间不存在");
    }

    public Room[][] getRooms() {
        return rooms;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void printRoomStatus(){
        System.out.println("当前酒店状态:");
        for(int i = 0; i < roomSize; i++)
            for(int j = 0; j < roomSize; j++) {
                Room thisRoom = rooms[i][j];
                System.out.println(
                        i + "楼" + j + "号房:" + (thisRoom.empty()? "未入住 ": "已入住 ") + "入住人数：" + thisRoom.getPeoNum()
                );
            }
    }

    public boolean existRoom(int floor, int no){
        return floor < roomSize && no < roomSize;
    }
}
