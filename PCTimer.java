import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class PCTimer
{
    public static int remainingTime = 0;
    public static JLabel timerLabel;
    public static Timer timer;

    public PCTimer() {
        timerLabel = new JLabel("00:00:00");
        timerLabel.setFont(new Font("Gothic", Font.BOLD, 50));
        timerLabel.setForeground(Color.WHITE);
    }

    public static void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remainingTime--;

                int hours = remainingTime / 3600;
                int minutes = (remainingTime % 3600) / 60;
                int seconds = remainingTime % 60;

                String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
                timerLabel.setText(timeString);

                if (remainingTime <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "시간이 종료되었습니다.");
                }
            }
        });

        timer.start();
    }
}