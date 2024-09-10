import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PCBangTimer {
    private MainProcess main;
    private static JCheckBox charge1CheckBox;
    private static JCheckBox charge2CheckBox;
    private static JCheckBox charge4CheckBox;
    private static JCheckBox charge6CheckBox;
    private static JCheckBox cardPaymentCheckBox;
    private static JCheckBox cashPaymentCheckBox;

    public PCBangTimer(PCTimer t) {
        JFrame frame = new JFrame("PCBang Timer : 12조");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 배경 이미지 패널 생성
        ImagePanel backgroundPanel = new ImagePanel(new ImageIcon("timetheme.jpg").getImage());
        backgroundPanel.setLayout(new BorderLayout());

        // 버튼 패널 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BorderLayout());

        // 버튼 생성
        JPanel chargePanel = new JPanel();
        chargePanel.setOpaque(false);
        chargePanel.setLayout(new FlowLayout());

        // 시간충전 체크박스(1,2,4,6시간 넷 중 하나만 선택하라고 이프문 썼음)
        charge1CheckBox = new JCheckBox("1시간 충전");
        charge1CheckBox.setPreferredSize(new Dimension(120, 30));
        charge1CheckBox.setFont(new Font("Gothic", Font.BOLD, 18));
        charge1CheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (charge1CheckBox.isSelected()) {
                    charge2CheckBox.setSelected(false);
                    charge4CheckBox.setSelected(false);
                    charge6CheckBox.setSelected(false);
                }
            }
        });

        charge2CheckBox = new JCheckBox("2시간 충전");
        charge2CheckBox.setPreferredSize(new Dimension(120, 30));
        charge2CheckBox.setFont(new Font("Gothic", Font.BOLD, 18));
        charge2CheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (charge2CheckBox.isSelected()) {
                    charge1CheckBox.setSelected(false);
                    charge4CheckBox.setSelected(false);
                    charge6CheckBox.setSelected(false);
                }
            }
        });

        charge4CheckBox = new JCheckBox("4시간 충전");
        charge4CheckBox.setPreferredSize(new Dimension(120, 30));
        charge4CheckBox.setFont(new Font("Gothic", Font.BOLD, 18));
        charge4CheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (charge4CheckBox.isSelected()) {
                    charge1CheckBox.setSelected(false);
                    charge2CheckBox.setSelected(false);
                    charge6CheckBox.setSelected(false);
                }
            }
        });

        charge6CheckBox = new JCheckBox("6시간 충전");
        charge6CheckBox.setPreferredSize(new Dimension(120, 30));
        charge6CheckBox.setFont(new Font("Gothic", Font.BOLD, 18));
        charge6CheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (charge6CheckBox.isSelected()) {
                    charge1CheckBox.setSelected(false);
                    charge2CheckBox.setSelected(false);
                    charge4CheckBox.setSelected(false);
                }
            }
        });

        chargePanel.add(charge1CheckBox);
        chargePanel.add(charge2CheckBox);
        chargePanel.add(charge4CheckBox);
        chargePanel.add(charge6CheckBox);

        JPanel paymentPanel = new JPanel();
        paymentPanel.setOpaque(false);
        paymentPanel.setLayout(new FlowLayout());

        // 결제 패널 생성 - 얘도 카드랑 현금 중 하나만 하라고 이프문 씀
        cardPaymentCheckBox = new JCheckBox("카드결제");
        cardPaymentCheckBox.setPreferredSize(new Dimension(110, 30));
        cardPaymentCheckBox.setFont(new Font("Gothic", Font.BOLD, 18));
        cardPaymentCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cardPaymentCheckBox.isSelected()) {
                    cashPaymentCheckBox.setSelected(false);
                }
            }
        });

        cashPaymentCheckBox = new JCheckBox("현금결제");
        cashPaymentCheckBox.setPreferredSize(new Dimension(110, 30));
        cashPaymentCheckBox.setFont(new Font("Gothic", Font.BOLD, 18));
        cashPaymentCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cashPaymentCheckBox.isSelected()) {
                    cardPaymentCheckBox.setSelected(false);
                }
            }
        });

        paymentPanel.add(cardPaymentCheckBox);
        paymentPanel.add(cashPaymentCheckBox);

        // 결제하기 버튼
        // 이거 n시간 충전하고 결제하면 n시간 충전완료했습니다 일케 띄웠음 (띠바 나 천잰가)
        JButton paymentButton = new JButton("결제하기");

        // 가로 길이와 세로 높이를 조정하기 위해 GridBagLayout 사용
        JPanel paymentButtonPanel = new JPanel(new GridBagLayout());
        paymentButtonPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 10, 10, 10); // 여백 조정

        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedHours = 0;

                if (charge1CheckBox.isSelected()) {
                    selectedHours = 1;
                } else if (charge2CheckBox.isSelected()) {
                    selectedHours = 2;
                } else if (charge4CheckBox.isSelected()) {
                    selectedHours = 4;
                } else if (charge6CheckBox.isSelected()) {
                    selectedHours = 6;
                }

                String paymentMethod = "";
                if (cardPaymentCheckBox.isSelected()) {
                    paymentMethod = "카드결제";
                } else if (cashPaymentCheckBox.isSelected()) {
                    paymentMethod = "현금결제";
                }

                if (selectedHours > 0 && !paymentMethod.isEmpty()) {
                    t.remainingTime += selectedHours * 60 * 60;
                    JOptionPane.showMessageDialog(null, selectedHours + "시간 충전이 완료되었습니다.");
                    t.startTimer();
                    main.showFrameTest3();
                    frame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "충전할 시간과 결제 방법을 선택해주세요.");
                }
            }

            private void showFrameTest3() {
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        paymentButtonPanel.add(paymentButton, gbc);

        JPanel paymentSelectionPanel = new JPanel();
        paymentSelectionPanel.setOpaque(false);
        paymentSelectionPanel.setLayout(new BorderLayout());

        JLabel paymentSelectionLabel = new JLabel("[결제방법 선택]\n\n");
        paymentSelectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        paymentSelectionLabel.setFont(new Font("Gothic", Font.BOLD, 30));
        paymentSelectionLabel.setForeground(Color.WHITE);
        paymentSelectionPanel.add(paymentSelectionLabel, BorderLayout.NORTH);
        paymentSelectionPanel.add(paymentPanel, BorderLayout.CENTER);


        JPanel paymentPanelWrapper = new JPanel();
        paymentPanelWrapper.setOpaque(false);
        paymentPanelWrapper.setLayout(new BorderLayout());
        paymentPanelWrapper.add(paymentSelectionPanel, BorderLayout.NORTH);
        paymentPanelWrapper.add(paymentButtonPanel, BorderLayout.CENTER);

        JLabel timeLabel = new JLabel("[시간 선택]\n");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Gothic", Font.BOLD, 30));
        timeLabel.setForeground(Color.WHITE);

        buttonPanel.add(timeLabel, BorderLayout.NORTH);
        buttonPanel.add(chargePanel, BorderLayout.CENTER);
        buttonPanel.add(paymentPanelWrapper, BorderLayout.SOUTH);


        JPanel timerPanel = new JPanel();
        timerPanel.setOpaque(false);
        timerPanel.setLayout(new BorderLayout());

        JLabel restLabel = new JLabel("잔여 시간");
        restLabel.setHorizontalAlignment(SwingConstants.CENTER);
        restLabel.setFont(new Font("Gothic", Font.BOLD, 30));
        restLabel.setForeground(Color.WHITE);

        t.timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerPanel.add(restLabel, BorderLayout.NORTH);
        timerPanel.add(t.timerLabel, BorderLayout.CENTER);

        backgroundPanel.add(timerPanel, BorderLayout.NORTH);
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.getContentPane().add(backgroundPanel);
        frame.pack();
        frame.setSize(1300, 800);
        frame.setVisible(true);
    }
    public void setMain(MainProcess main) {


        this.main = main;

    }
    // 배경 이미지 표시를 위한 커스텀 JPanel
    static class ImagePanel extends JPanel {
        private Image image;

        public ImagePanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
    public void dispose() {
    }
}