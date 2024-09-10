import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginView extends JFrame {
    private MainProcess main;
    private JButton bLogin;
    private JButton bInit;
    private JPasswordField passText;
    private JTextField userText;
    private boolean bLoginCheck;

    public LoginView() {
        // setting
        setTitle("로그인 화면");
        setSize(1300, 800);
        setResizable(false);
        setLocation(150, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        // panel
        JPanel panel = new JPanel() {
            @Override
            @SuppressWarnings("serial")
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                // Set the background image

                ImageIcon imageIcon = new ImageIcon("logintheme.jpg");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

                ImageIcon logoIcon = new ImageIcon("아이센스.png");
                Image logoImage = logoIcon.getImage();
                int logoX = (getWidth() - logoIcon.getIconWidth()) / 2;
                int logoY = 80;
                logoIcon.paintIcon(this, g, logoX, logoY);
            }
        };
        panel.setLayout(null);
        placeLoginPanel(panel);

        // add
        add(panel);

        // visible
        setVisible(true);
    }


    public void placeLoginPanel(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("ID");
        userLabel.setFont(new Font("Dialog", Font.BOLD, 35));
        userLabel.setBounds(420, 415, 80, 25);
        userLabel.setForeground(Color.WHITE); // Set text color to white
        panel.add(userLabel);

        JLabel passLabel = new JLabel("PW");
        passLabel.setFont(new Font("Dialog", Font.BOLD, 35));
        passLabel.setBounds(420, 480, 80, 25);
        passLabel.setForeground(Color.WHITE); // Set text color to white
        panel.add(passLabel);

        Font font = new Font("Arial", Font.PLAIN, 40); // 원하는 폰트와 크기로 설정

        userText = new JTextField(30);
        userText.setBounds(520, 390, 320, 60); // Doubled the width and height
        userText.setFont(font); // 폰트 설정
        panel.add(userText);

        passText = new JPasswordField(30);
        passText.setBounds(520, 460, 320, 60); // Doubled the width and height
        passText.setFont(font); // 폰트 설정
        panel.add(passText);
        passText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });

        bInit = new JButton("초기화");
        bInit.setBounds(420, 550, 200, 60); // Doubled the width and height
        bInit.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        panel.add(bInit);
        bInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userText.setText("");
                passText.setText("");
            }
        });

        bLogin = new JButton("로그인");
        bLogin.setBounds(640, 550, 200, 60); // Doubled the width and height
        bLogin.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        panel.add(bLogin);
        bLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });
    }

    public void isLoginCheck() {
        if (userText.getText().equals("pc12") && new String(passText.getPassword()).equals("1234")) {
            JOptionPane.showMessageDialog(null, "pc12님 로그인 되었습니다 \n 메인화면으로 이동합니다", "로그인 성공",
                    JOptionPane.INFORMATION_MESSAGE);
            bLoginCheck = true;

            // 로그인 성공이라면 창 띄우기
            if (isLogin()) {
                main.showFrameTest(); // 메인창 메소드를 이용해 창 띄우기
            }


        }
        else {
            JOptionPane.showMessageDialog(

                    null, "아이디 또는 비밀번호를 잘못 입력했습니다 \n 입력하신 내용을 다시 확인해주세요", "로그인 실패",
                    JOptionPane.WARNING_MESSAGE);
        }
    }




// mainProcess와 연동


    public void setMain(MainProcess main) {


        this.main = main;

    }

    public boolean isLogin() {


        return bLoginCheck;
    }
}