import javax.swing.*;

public class MainProcess{
    LoginView login;
    PCBangTimer timer;
    MenuOrderMain menu;
    LoadingThread load;
    PayThread pay;
    PCTimer t;
    public static void main(String[] args) {

        // 메인클래스 실행
        MainProcess main = new MainProcess();
        main.t = new PCTimer();
        main.login= new LoginView(); // 로그인창 보이기
        main.login.setMain(main); // 로그인창에게 메인 클래스보내기
    }

    public void showFrameTest(){
        login.dispose(); // 로그인창닫기
        this.load = new LoadingThread(); // 테스트프레임 오픈
        this.load.setMain(this);
    }
    public void showFrameTest0(){
        load.dispose(); // 로그인창닫기
        this.timer = new PCBangTimer(t); // 테스트프레임 오픈
        this.timer.setMain(this);
    }

    public void showFrameTest1(){
        timer.dispose(); // 로그인창닫기
        this.menu = new MenuOrderMain(t); // 테스트프레임 오픈
    }
    public void showFrameTest2(){
        menu.dispose();
        this.login = new LoginView();
    }

    public void showFrameTest3(){
        timer.dispose();
        this.pay = new PayThread();
        this.pay.setMain(this);
    }

}