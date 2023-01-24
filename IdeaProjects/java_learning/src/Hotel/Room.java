package Hotel;

/**
 * 房间类，储存房间的信息，
 * 入住的人数，入住时间，退房时间，是否入住
 */
public class Room {
    private int peoNum;
    private int checkInTime;
    private int checkOutTime;

    public Room() {
    }

    public Room(int peoNum) {
        this(peoNum,0,0);
    }

    public Room(int peoNum, int checkInTime, int checkOutTime) {
        this.peoNum = peoNum;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public int getPeoNum() {

        return peoNum;
    }

    public void setPeoNum(int peoNum) {
        this.peoNum = peoNum;
    }

    public int getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(int checkInTime) {
        this.checkInTime = checkInTime;
    }

    public int getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(int checkOutTime) {
        this.checkOutTime = checkOutTime;
    }


    public void chekOut(){
        peoNum = 0;
        checkInTime = 0;
        checkOutTime = 0;
    }
    public void checkIn(int peoNum){
        this.peoNum = peoNum;
    }

    public boolean empty(){
        return peoNum == 0;
    }

}
