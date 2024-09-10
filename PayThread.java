import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PayThread extends JFrame {
    private MainProcess main;
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private static final int IMAGE_SIZE = 100;
    private static final String IMAGE_PATH = "clover.png";
    private static final String BACKGROUND_PATH = "bg.jpg";

    private JLabel imageLabel;
    private int imageX;
    private int imageY;
    private int dx;
    private int dy;
    private double rotationAngle;  // 이미지의 이동 각도

    private JLabel loadingLabel;
    private String loadingText;
    private int currentIndex;

    public PayThread() {
        setTitle("Payment Loading");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        setLayout(null); // 레이아웃 매니저를 사용하지 않음

        // 배경화면 설정
        setContentPane(new JLabel(new ImageIcon(BACKGROUND_PATH)));

        imageLabel = new JLabel(new ImageIcon(IMAGE_PATH));
        imageLabel.setSize(IMAGE_SIZE, IMAGE_SIZE);
        add(imageLabel);

        loadingLabel = new JLabel();
        loadingLabel.setFont(new Font("Gothic", Font.BOLD, 35));  // 폰트 크기와 스타일 변경
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingLabel.setBounds(50, 150, 300, 50);
        add(loadingLabel);

        setVisible(true);

        startMoving();
        startLoadingTextAnimation();
        java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dispose(); // 현재 클래스 종료
                main.showFrameTest1(); // PCBangTimer 클래스 실행
            }
        }, 5000);
    }

    private void startMoving() {
        Thread movingThread = new Thread(() -> {
            Random random = new Random();

            // 초기 위치 및 방향 설정
            imageX = (FRAME_WIDTH - IMAGE_SIZE) / 2;
            imageY = (FRAME_HEIGHT - IMAGE_SIZE) / 2;
            dx = random.nextInt(6) - 3;  // -2부터 2까지의 랜덤한 값을 가짐
            dy = random.nextInt(5) - 2;

            while (true) {
                moveImage();

                // 이미지가 창의 가장자리에 닿으면 반대 방향으로 튕기도록 변경
                if (imageX <= 0 || imageX >= FRAME_WIDTH - IMAGE_SIZE) {
                    dx = -dx;
                    rotationAngle = Math.toRadians(random.nextDouble() * 90); // 0부터 90도까지의 랜덤한 각도 설정
                }
                if (imageY <= 0 || imageY >= FRAME_HEIGHT - IMAGE_SIZE) {
                    dy = -dy;
                    rotationAngle = Math.toRadians(random.nextDouble() * 90); // 0부터 90도까지의 랜덤한 각도 설정
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        movingThread.start();
    }

    private void moveImage() {
        imageX += dx;
        imageY += dy;

        // 이미지의 위치가 창의 범위를 벗어나지 않도록 보정
        if (imageX < 0) {
            imageX = 0;
            dx = -dx;
        } else if (imageX > FRAME_WIDTH - IMAGE_SIZE) {
            imageX = FRAME_WIDTH - IMAGE_SIZE;
            dx = -dx;
        }
        if (imageY < 0) {
            imageY = 0;
            dy = -dy;
        } else if (imageY > FRAME_HEIGHT - IMAGE_SIZE) {
            imageY = FRAME_HEIGHT - IMAGE_SIZE;
            dy = -dy;
        }

        imageLabel.setLocation(imageX, imageY);

        // 이미지 회전 및 뒤집기
        Image originalImage = new ImageIcon(IMAGE_PATH).getImage();
        Image transformedImage = transformImage(originalImage, rotationAngle);
        imageLabel.setIcon(new ImageIcon(transformedImage));
    }

    private Image transformImage(Image image, double angle) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage transformedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = transformedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.rotate(angle, width / 2, height / 2);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return transformedImage;
    }
    public void setMain(MainProcess main) {


        this.main = main;

    }
    private void startLoadingTextAnimation() {
        Thread PayThread = new Thread(() -> {
            String text = "결제중..";
            currentIndex = 0;
            while (true) {
                loadingText = text.substring(0, currentIndex + 1);
                SwingUtilities.invokeLater(() -> loadingLabel.setText(loadingText));

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                currentIndex++;
                if (currentIndex >= text.length()) {
                    currentIndex = 0;
                    SwingUtilities.invokeLater(() -> loadingLabel.setText(""));
                }
            }
        });

        PayThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PayThread::new);
    }
}