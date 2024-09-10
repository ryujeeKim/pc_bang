import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MenuOrderMain {

	// JPanel 속성을 상속받은 이미지 패널 설정, 이미지를 그리는 도구
	@SuppressWarnings("serial")
	static class ImagePanel extends JPanel{
		private Image img;
		private Image manual_img = new ImageIcon("pc게임.png").getImage();

		public ImagePanel (Image img) {
			this.img = img;
		}

		// 그리기 도구 배경화면, 사용서 이미지 그리기
		public void paintComponent (Graphics g) {
			g.drawImage(img, 0,0,1300,800,null);
			g.drawImage(manual_img,715,140,470,450,null);
		}
	}



	public MenuOrderMain(PCTimer t) {
		JFrame fr = new JFrame("PC방_메뉴주문(12조)");
		// 이미지 패널 그리기
		ImagePanel pn =new ImagePanel(new ImageIcon("theme.jpg").getImage());
		fr.pack();
		fr.add(pn);
		JPanel Mealpn = new JPanel();
		JPanel Drinkpn = new JPanel();
		JPanel Snackpn = new JPanel();
		JPanel Sidepn = new JPanel();
		JPanel Orderpn = new JPanel();

		// 주문내역
		JTextArea ordertxt = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(ordertxt);
		scrollPane.setBounds(248, 610, 610, 120); // 스크롤 패널의 크기 및 위치 설정

		t.timerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		pn.add(t.timerLabel);
		// 스크롤 가능한 컨테이너로 감싸진 ordertxt를 원하는 컨테이너에 추가
		pn.add(scrollPane);

		ordertxt.append("<주문 목록> \n\n");
		ordertxt.setEditable(false);
		ordertxt.setFont(new Font("맑은 고딕", Font.BOLD, 17));



		JTextArea listtxt = new JTextArea();
		listtxt.setBounds(0,0, 1600, 600);
		listtxt.setFont(new Font("맑은 고딕",Font.BOLD,12));

		JButton[] bt = new JButton[6];
		JButton order_bt = new JButton("주문담기");
		JButton logout = new JButton("로그아웃");

		// 메뉴판 테이블 만들기
		String [] heading = new String[] {"상품명","가격"};
		Object[][] data = new Object [][] {
				{"상품명","가격"},
				{"제육덮밥","6500원"},
				{"라면","3500원"},
				{"만두라면","4500원"},
				{"너구리","3500원"},
				{"김치볶음밥","5500원"},
				{"참치마요덮밥","5000원"},
				{"콜라","2000원"},
				{"핫식스","2000원"},
				{"사이다","2000원"},
				{"포카리스웨트","1500원"},
				{"초코에몽","1500원"},
				{"웰치스","2000원"},
				{"꼬북칩","2000원"},
				{"포스틱","2000원"},
				{"초코송이","1000원"},
				{"새우깡","2000원"},
				{"사또밥","2000원"},
				{"눈을감자","1500원"},
				{"김치","0원"},
				{"단무지","0원"}

		};

		JTable table = new JTable(data,heading);
		pn.add(new JScrollPane(table));
		pn.add(table);
		table.setBounds(250, 140, 470, 450);
		table.setFont(new Font("맑은 고딕",Font.BOLD,17));
		table.setRowHeight(50);


		// 버튼 위치와 크기 설정
		int width[]= {200,200,200,200,200,200};
		int height[]= {50,50,50,50,50,50};
		int x[] = {230,480,730,980,50,50};
		int y[] = {60,60,60,60,200,380};

		// 이미지 패널 크기설정
		pn.setLayout(null);
		pn.setBounds(0,0,1300,800);
		// 판매할 메뉴 Vector 객체 속에 미리 저장
		MenuOrder.setupMenu();

		// 메인 버튼 6개 설정
		bt[0] = new JButton("Meal");
		bt[1] = new JButton("Drink");
		bt[2] = new JButton("Snack");
		bt[3] = new JButton("Side");
		bt[4] = new JButton("Main");
		bt[5] = new JButton("Order");




		for (int i = 0; i < bt.length; i++) {
			pn.add(bt[i]);
			bt[i].setBounds(x[i], y[i], width[i], height[i]);
			// 폰트 설정
			bt[i].setFont(new Font("Dialog", Font.BOLD + Font.ITALIC, 40));
			// 버튼을 투명하게 만들고, 버튼글씨 색상 설정
			bt[i].setContentAreaFilled(false);
			bt[i].setBorderPainted(false);
			bt[i].setFocusPainted(false);
			bt[i].setForeground(Color.WHITE);
		}

		// 버튼 0번의 기능설정
		bt[0].addActionListener(new ActionListener () {

									@Override
									public void actionPerformed(ActionEvent e) {
										JButton srcBtn =(JButton)e.getSource();
										if(srcBtn==bt[0]) {
											doMeal();

											// Mealpn의 크기 및 색상
											Mealpn.setLayout(null);
											Mealpn.setBounds(250, 140, 945, 460);
											Mealpn.setBackground(Color.lightGray);

											// Mealpn 누를 시, 나머지 패널을 보이지 않게 하기
											Mealpn.setVisible(true);
											Drinkpn.setVisible(false);
											Snackpn.setVisible(false);
											Sidepn.setVisible(false);
											Orderpn.setVisible(false);
											ordertxt.setVisible(true);

											table.setVisible(false);
											order_bt.setVisible(true);
											logout.setVisible(false);
										}
									}

									private void doMeal() {

										// Mealpn에 생성할 이미지 버튼을 설정
										JButton MealButton_제육덮밥 = new JButton("");
										fr.add(Mealpn);
										MealButton_제육덮밥.setIcon(new ImageIcon("제육덮밥.jpg"));
										Mealpn.add(MealButton_제육덮밥);
										MealButton_제육덮밥.setBounds(20, 20, 260,185);
										// 메뉴이름 띄울 텍스트 필드 생성
										JTextField 제육덮밥 = new JTextField("제육덮밥  6500원");
										Mealpn.add(제육덮밥);
										제육덮밥.setBounds(20,205,260,30);
										제육덮밥.setEditable(false);

										// 제육덮밥을 누르면 알림창이 뜨는 기능 설정
										MealButton_제육덮밥.addActionListener(new ActionListener () {

											@Override
											public void actionPerformed(ActionEvent e) {
												JButton srcBtn =(JButton)e.getSource();
												if(srcBtn==MealButton_제육덮밥) {
													int result=   JOptionPane.showConfirmDialog(MealButton_제육덮밥, "제육덮밥을 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
													if(result == JOptionPane.OK_OPTION) {
														ordertxt.append((MenuOrder.meal.get(0).toString()));
														MenuOrder.order.add(MenuOrder.meal.get(0));
														MenuOrder.new_Price(6500);
													}
												}
											}});
										JButton MealButton_라면 = new JButton("");
										MealButton_라면.setIcon(new ImageIcon("라면.jpg"));
										Mealpn.add(MealButton_라면);
										MealButton_라면.setBounds(280, 20, 259, 185);
										// 메뉴이름 띄울 텍스트 필드 생성
										JTextField 라면 = new JTextField("라면  3500원");
										Mealpn.add(라면);
										라면.setBounds(280,205,260,30);
										라면.setEditable(false);
										// 라면을 누르면 알림창이 뜨는 기능 설정
										MealButton_라면.addActionListener(new ActionListener () {
											@Override
											public void actionPerformed(ActionEvent e) {
												JButton srcBtn =(JButton)e.getSource();
												if(srcBtn==MealButton_라면) {
													int result = JOptionPane.showConfirmDialog(MealButton_라면, "라면을 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
													if(result == JOptionPane.OK_OPTION) {
														ordertxt.append((MenuOrder.meal.get(1).toString()));
														MenuOrder.order.add(MenuOrder.meal.get(1));
														MenuOrder.new_Price(3500);

													}

												}
											}});

										JButton MealButton_만두라면 = new JButton("");
										MealButton_만두라면.setIcon(new ImageIcon("만두라면.jpg"));
										Mealpn.add(MealButton_만두라면);
										MealButton_만두라면.setBounds(540, 20, 259, 185);
										// 메뉴이름 띄울 텍스트 필드 생성
										JTextField 만두라면 = new JTextField("만두라면  4500원");
										Mealpn.add(만두라면);
										만두라면.setBounds(540,205,260,30);
										만두라면.setEditable(false);
										// 만두라면을 누르면 알림창이 뜨는 기능 설정
										MealButton_만두라면.addActionListener(new ActionListener () {
											@Override
											public void actionPerformed(ActionEvent e) {
												JButton srcBtn =(JButton)e.getSource();
												if(srcBtn==MealButton_만두라면) {
													int result = JOptionPane.showConfirmDialog(MealButton_만두라면, "만두라면을 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
													if(result == JOptionPane.OK_OPTION) {
														ordertxt.append((MenuOrder.meal.get(2).toString()));
														MenuOrder.order.add(MenuOrder.meal.get(2));
														MenuOrder.new_Price(4500);

													}

												}
											}});
										JButton MealButton_너구리 = new JButton("");
										MealButton_너구리.setIcon(new ImageIcon("너구리.jpg"));
										Mealpn.add(MealButton_너구리);
										MealButton_너구리.setBounds(20, 240, 259, 185);
										// 메뉴이름 띄울 텍스트 필드 생성
										JTextField 너구리 = new JTextField("너구리  3500원");
										Mealpn.add(너구리);
										너구리.setBounds(20,425,260,30);
										너구리.setEditable(false);
										// 너구리를 누르면 알림창이 뜨는 기능 설정
										MealButton_너구리.addActionListener(new ActionListener () {
											@Override
											public void actionPerformed(ActionEvent e) {
												JButton srcBtn =(JButton)e.getSource();
												if(srcBtn==MealButton_너구리) {
													int result = JOptionPane.showConfirmDialog(MealButton_너구리, "너구리를 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
													if(result == JOptionPane.OK_OPTION) {
														ordertxt.append((MenuOrder.meal.get(3).toString()));
														MenuOrder.order.add(MenuOrder.meal.get(3));
														MenuOrder.new_Price(3500);

													}

												}
											}});
										JButton MealButton_김치볶음밥 = new JButton("");
										MealButton_김치볶음밥.setIcon(new ImageIcon("김치볶음밥.jpeg"));
										Mealpn.add(MealButton_김치볶음밥);
										MealButton_김치볶음밥.setBounds(280, 240, 259, 185);
										// 메뉴이름 띄울 텍스트 필드 생성
										JTextField 김치볶음밥 = new JTextField("김치볶음밥  5500원");
										Mealpn.add(김치볶음밥);
										김치볶음밥.setBounds(280,425,260,30);
										김치볶음밥.setEditable(false);
										// 김치볶음밥을 누르면 알림창이 뜨는 기능 설정
										MealButton_김치볶음밥.addActionListener(new ActionListener () {
											@Override
											public void actionPerformed(ActionEvent e) {
												JButton srcBtn =(JButton)e.getSource();
												if(srcBtn==MealButton_김치볶음밥) {
													int result = JOptionPane.showConfirmDialog(MealButton_김치볶음밥, "김치볶음밥을 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
													if(result == JOptionPane.OK_OPTION) {
														ordertxt.append((MenuOrder.meal.get(4).toString()));
														MenuOrder.order.add(MenuOrder.meal.get(4));
														MenuOrder.new_Price(5500);

													}

												}
											}});
										JButton MealButton_참치마요덮밥 = new JButton("");
										MealButton_참치마요덮밥.setIcon(new ImageIcon("참치마요덮밥.jpeg"));
										Mealpn.add(MealButton_참치마요덮밥);
										MealButton_참치마요덮밥.setBounds(540, 240, 259, 185);
										// 메뉴이름 띄울 텍스트 필드 생성
										JTextField 참치마요덮밥 = new JTextField("참치마요덮밥  5000원");
										Mealpn.add(참치마요덮밥);
										참치마요덮밥.setBounds(540,425,260,30);
										참치마요덮밥.setEditable(false);
										// 만두라면을 누르면 알림창이 뜨는 기능 설정
										MealButton_참치마요덮밥.addActionListener(new ActionListener () {
											@Override
											public void actionPerformed(ActionEvent e) {
												JButton srcBtn =(JButton)e.getSource();
												if(srcBtn==MealButton_참치마요덮밥) {
													int result = JOptionPane.showConfirmDialog(MealButton_참치마요덮밥, "참치마요덮밥을 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
													if(result == JOptionPane.OK_OPTION) {
														ordertxt.append((MenuOrder.meal.get(5).toString()));
														MenuOrder.order.add(MenuOrder.meal.get(5));
														MenuOrder.new_Price(5000);

													}

												}
											}});
									}


								}

		);
		// bt[0] 과 같은 형식임.
		bt[1].addActionListener(new ActionListener () {

									@Override
									public void actionPerformed(ActionEvent e) {
										JButton srcBtn =(JButton)e.getSource();
										if(srcBtn==bt[1]) {
											doDrink();

											Drinkpn.setLayout(null);
											Drinkpn.setBounds(250, 140, 945, 460);
											Drinkpn.setBackground(Color.lightGray);

											Mealpn.setVisible(false);
											Drinkpn.setVisible(true);
											Snackpn.setVisible(false);
											Sidepn.setVisible(false);
											Orderpn.setVisible(false);
											ordertxt.setVisible(true);

											table.setVisible(false);
											order_bt.setVisible(true);
										}
									}

									private void doDrink() {

										fr.add(Drinkpn);
										JButton DrinkButton_콜라 = new JButton("");
										DrinkButton_콜라.setIcon(new ImageIcon("콜라.jpg"));
										Drinkpn.add(DrinkButton_콜라);
										DrinkButton_콜라.setBounds(20, 20, 260,185);
										// 메뉴이름 띄울 텍스트 필드 생성
										JTextField 콜라 = new JTextField("콜라  2000원");
										Drinkpn.add(콜라);
										콜라.setBounds(20,205,260,30);
										콜라.setEditable(false);
										// 버튼 기능 추가
										DrinkButton_콜라.addActionListener(new ActionListener () {

											@Override
											public void actionPerformed(ActionEvent e) {
												JButton srcBtn =(JButton)e.getSource();
												if(srcBtn==DrinkButton_콜라) {
													int result = JOptionPane.showConfirmDialog(DrinkButton_콜라, "콜라를 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
													if(result == JOptionPane.OK_OPTION) {
														ordertxt.append((MenuOrder.drink.get(0).toString()));
														MenuOrder.order.add(MenuOrder.drink.get(0));
														MenuOrder.new_Price(2000);
													}

												}
											}});

										JButton DrinkButton_핫식스 = new JButton("");
										DrinkButton_핫식스.setIcon(new ImageIcon("핫식스.jpg"));
										Drinkpn.add(DrinkButton_핫식스);
										DrinkButton_핫식스.setBounds(280, 20, 259,185);
										// 메뉴이름 띄울 텍스트 필드 생성
										JTextField 핫식스 = new JTextField("핫식스  2000원");
										Drinkpn.add(핫식스);
										핫식스.setBounds(280,205,260,30);
										핫식스.setEditable(false);
										// 버튼 기능 추가
										DrinkButton_핫식스.addActionListener(new ActionListener () {

											@Override
											public void actionPerformed(ActionEvent e) {
												JButton srcBtn =(JButton)e.getSource();
												if(srcBtn==DrinkButton_핫식스) {
													int result = JOptionPane.showConfirmDialog(DrinkButton_핫식스, "핫식스를 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
													if(result == JOptionPane.OK_OPTION) {
														ordertxt.append((MenuOrder.drink.get(1).toString()));
														MenuOrder.order.add(MenuOrder.drink.get(1));
														MenuOrder.new_Price(2000);
													}

												}
											}});

										JButton DrinkButton_사이다 = new JButton("");
										DrinkButton_사이다.setIcon(new ImageIcon("사이다.png"));
										Drinkpn.add(DrinkButton_사이다);
										DrinkButton_사이다.setBounds(540, 20, 259,185);
										// 메뉴이름 띄울 텍스트 필드 생성
										JTextField 사이다 = new JTextField("사이다  2000원");
										Drinkpn.add(사이다);
										사이다.setBounds(540,205,260,30);
										사이다.setEditable(false);
										// 버튼 기능 추가
										DrinkButton_사이다.addActionListener(new ActionListener () {

											@Override
											public void actionPerformed(ActionEvent e) {
												JButton srcBtn =(JButton)e.getSource();
												if(srcBtn==DrinkButton_사이다) {
													int result = JOptionPane.showConfirmDialog(DrinkButton_사이다, "사이다를 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
													if(result == JOptionPane.OK_OPTION) {
														ordertxt.append((MenuOrder.drink.get(2).toString()));
														MenuOrder.order.add(MenuOrder.drink.get(2));
														MenuOrder.new_Price(2000);
													}

												}
											}});

										JButton DrinkButton_포카리스웨트 = new JButton("");
										DrinkButton_포카리스웨트.setIcon(new ImageIcon("포카리스웨트.png"));
										Drinkpn.add(DrinkButton_포카리스웨트);
										DrinkButton_포카리스웨트.setBounds(20, 240, 259,185);
										// 메뉴이름 띄울 텍스트 필드 생성
										JTextField 포카리스웨트 = new JTextField(" 포카리스웨트 1500원");
										Drinkpn.add(포카리스웨트);
										포카리스웨트.setBounds(20,425,260,30);
										포카리스웨트.setEditable(false);
										// 버튼 기능 추가
										DrinkButton_포카리스웨트.addActionListener(new ActionListener () {

											@Override
											public void actionPerformed(ActionEvent e) {
												JButton srcBtn =(JButton)e.getSource();
												if(srcBtn==DrinkButton_포카리스웨트) {
													int result = JOptionPane.showConfirmDialog(DrinkButton_포카리스웨트, "포카리스웨트를 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
													if(result == JOptionPane.OK_OPTION) {
														ordertxt.append((MenuOrder.drink.get(3).toString()));
														MenuOrder.order.add(MenuOrder.drink.get(3));
														MenuOrder.new_Price(1500);
													}

												}
											}});

										JButton DrinkButton_초코에몽 = new JButton("");
										DrinkButton_초코에몽.setIcon(new ImageIcon("초코에몽.png"));
										Drinkpn.add(DrinkButton_초코에몽);
										DrinkButton_초코에몽.setBounds(280, 240, 259,185);
										// 메뉴이름 띄울 텍스트 필드 생성
										JTextField 초코에몽 = new JTextField("초코에몽  1500원");
										Drinkpn.add(초코에몽);
										초코에몽.setBounds(280,425,260,30);
										초코에몽.setEditable(false);
										// 버튼 기능 추가
										DrinkButton_초코에몽.addActionListener(new ActionListener () {

											@Override
											public void actionPerformed(ActionEvent e) {
												JButton srcBtn =(JButton)e.getSource();
												if(srcBtn==DrinkButton_초코에몽) {
													int result = JOptionPane.showConfirmDialog(DrinkButton_초코에몽, "초코에몽을 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
													if(result == JOptionPane.OK_OPTION) {
														ordertxt.append((MenuOrder.drink.get(4).toString()));
														MenuOrder.order.add(MenuOrder.drink.get(4));
														MenuOrder.new_Price(1500);
													}

												}
											}});

										JButton DrinkButton_웰치스 = new JButton("");
										DrinkButton_웰치스.setIcon(new ImageIcon("웰치스.png"));
										Drinkpn.add(DrinkButton_웰치스);
										DrinkButton_웰치스.setBounds(540, 240, 259,185);
										// 메뉴이름 띄울 텍스트 필드 생성
										JTextField 웰치스 = new JTextField("웰치스 2000원");
										Drinkpn.add(웰치스);
										웰치스.setBounds(540,425,260,30);
										웰치스.setEditable(false);
										// 버튼 기능 추가
										DrinkButton_웰치스.addActionListener(new ActionListener () {

											@Override
											public void actionPerformed(ActionEvent e) {
												JButton srcBtn =(JButton)e.getSource();
												if(srcBtn==DrinkButton_웰치스) {
													int result = JOptionPane.showConfirmDialog(DrinkButton_웰치스, "웰치스를 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
													if(result == JOptionPane.OK_OPTION) {
														ordertxt.append((MenuOrder.drink.get(5).toString()));
														MenuOrder.order.add(MenuOrder.drink.get(5));
														MenuOrder.new_Price(2000);
													}

												}
											}});
									}

								}

		);

		bt[2].addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton srcBtn = (JButton)e.getSource();
				if(srcBtn==bt[2]) {
					doSnack();

					Snackpn.setLayout(null);
					Snackpn.setBounds(250, 140, 945, 460);
					Snackpn.setBackground(Color.lightGray);
					Mealpn.setVisible(false);
					Drinkpn.setVisible(false);
					Snackpn.setVisible(true);
					Sidepn.setVisible(false);
					Orderpn.setVisible(false);
					ordertxt.setVisible(true);

					table.setVisible(false);
					order_bt.setVisible(true);
				}
			}

			private void doSnack() {
				fr.add(Snackpn);
				JButton SnackButton_꼬북칩 = new JButton(new ImageIcon("꼬북칩.jpg"));
				Snackpn.add(SnackButton_꼬북칩);
				SnackButton_꼬북칩.setBounds(20, 20, 260,185);
				// 메뉴이름 띄울 텍스트 필드 생성
				JTextField 꼬북칩 = new JTextField("꼬북칩  2000원");
				Snackpn.add(꼬북칩);
				꼬북칩.setBounds(20,205,260,30);
				꼬북칩.setEditable(false);
				SnackButton_꼬북칩.addActionListener(new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent e) {
						JButton srcBtn =(JButton)e.getSource();
						if(srcBtn==SnackButton_꼬북칩) {
							int result=   JOptionPane.showConfirmDialog(SnackButton_꼬북칩, "꼬북칩을 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
							if(result == JOptionPane.OK_OPTION) {
								ordertxt.append((MenuOrder.snack.get(0).toString()));
								MenuOrder.order.add(MenuOrder.snack.get(0));
								MenuOrder.new_Price(2000);
							}
						}
					}
				});

				JButton SnackButton_포스틱 = new JButton("");
				SnackButton_포스틱.setIcon(new ImageIcon("포스틱.jpg"));
				Snackpn.add(SnackButton_포스틱);
				SnackButton_포스틱.setBounds(280, 20, 259,185);
				// 메뉴이름 띄울 텍스트 필드 생성
				JTextField 포스틱 = new JTextField("포스틱  2000원");
				Snackpn.add(포스틱);
				포스틱.setBounds(280,205,260,30);
				포스틱.setEditable(false);
				SnackButton_포스틱.addActionListener(new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent e) {
						JButton srcBtn =(JButton)e.getSource();
						if(srcBtn==SnackButton_포스틱) {
							int result=   JOptionPane.showConfirmDialog(SnackButton_포스틱, "포스틱을 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
							if(result == JOptionPane.OK_OPTION) {
								ordertxt.append((MenuOrder.snack.get(1).toString()));
								MenuOrder.order.add(MenuOrder.snack.get(1));
								MenuOrder.new_Price(2000);
							}
						}
					}
				});
				JButton SnackButton_초코송이 = new JButton("");
				SnackButton_초코송이.setIcon(new ImageIcon("초코송이.jpg"));
				Snackpn.add(SnackButton_초코송이);
				SnackButton_초코송이.setBounds(540, 20, 259,185);
				// 메뉴이름 띄울 텍스트 필드 생성
				JTextField 초코송이 = new JTextField("초코송이 1000원");
				Snackpn.add(초코송이);
				초코송이.setBounds(540,205,260,30);
				초코송이.setEditable(false);
				SnackButton_초코송이.addActionListener(new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent e) {
						JButton srcBtn =(JButton)e.getSource();
						if(srcBtn==SnackButton_초코송이) {
							int result=   JOptionPane.showConfirmDialog(SnackButton_초코송이, "초코송이를 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
							if(result == JOptionPane.OK_OPTION) {
								ordertxt.append((MenuOrder.snack.get(2).toString()));
								MenuOrder.order.add(MenuOrder.snack.get(2));
								MenuOrder.new_Price(1000);
							}
						}
					}
				});

				JButton SnackButton_새우깡 = new JButton("");
				SnackButton_새우깡.setIcon(new ImageIcon("새우깡.png"));
				Snackpn.add(SnackButton_새우깡);
				SnackButton_새우깡.setBounds(20, 240, 259, 185);
				// 메뉴이름 띄울 텍스트 필드 생성
				JTextField  새우깡= new JTextField("새우깡  2000원");
				Snackpn.add(새우깡);
				새우깡.setBounds(20,425,260,30);
				새우깡.setEditable(false);
				SnackButton_새우깡.addActionListener(new ActionListener () {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton srcBtn =(JButton)e.getSource();
						if(srcBtn==SnackButton_새우깡) {
							int result = JOptionPane.showConfirmDialog(SnackButton_새우깡, "새우깡을 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
							if(result == JOptionPane.OK_OPTION) {
								ordertxt.append((MenuOrder.snack.get(3).toString()));
								MenuOrder.order.add(MenuOrder.snack.get(3));
								MenuOrder.new_Price(2000);

							}

						}
					}});
				JButton SnackButton_사또밥 = new JButton("");
				SnackButton_사또밥.setIcon(new ImageIcon("사또밥.png"));
				Snackpn.add(SnackButton_사또밥);
				SnackButton_사또밥.setBounds(280, 240, 259, 185);
				// 메뉴이름 띄울 텍스트 필드 생성
				JTextField  사또밥= new JTextField("사또밥  2000원");
				Snackpn.add(사또밥);
				사또밥.setBounds(280, 425, 260, 30);
				사또밥.setEditable(false);
				SnackButton_사또밥.addActionListener(new ActionListener () {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton srcBtn =(JButton)e.getSource();
						if(srcBtn==SnackButton_사또밥) {
							int result = JOptionPane.showConfirmDialog(SnackButton_사또밥, "사또밥을 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
							if(result == JOptionPane.OK_OPTION) {
								ordertxt.append((MenuOrder.snack.get(4).toString()));
								MenuOrder.order.add(MenuOrder.snack.get(4));
								MenuOrder.new_Price(2000);

							}

						}
					}});
				JButton SnackButton_눈을감자 = new JButton("");
				SnackButton_눈을감자.setIcon(new ImageIcon("눈을감자.png"));
				Snackpn.add(SnackButton_눈을감자);
				SnackButton_눈을감자.setBounds(540, 240, 259, 185);
				// 메뉴이름 띄울 텍스트 필드 생성
				JTextField 눈을감자 = new JTextField("눈을감자 1500원");
				Snackpn.add(눈을감자);
				눈을감자.setBounds(540,425,260,30);
				눈을감자.setEditable(false);
				SnackButton_눈을감자.addActionListener(new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent e) {
						JButton srcBtn =(JButton)e.getSource();
						if(srcBtn==SnackButton_눈을감자) {
							int result=   JOptionPane.showConfirmDialog(SnackButton_눈을감자, "눈을감자를 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
							if(result == JOptionPane.OK_OPTION) {
								ordertxt.append((MenuOrder.snack.get(5).toString()));
								MenuOrder.order.add(MenuOrder.snack.get(5));
								MenuOrder.new_Price(1500);
							}
						}
					}
				});

			}});

		bt[3].addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton srcBtn = (JButton)e.getSource();
				if(srcBtn==bt[3]) {
					doSide();

					Sidepn.setLayout(null);
					Sidepn.setBounds(250, 140, 945, 460);
					Sidepn.setBackground(Color.lightGray);

					Mealpn.setVisible(false);
					Drinkpn.setVisible(false);
					Snackpn.setVisible(false);
					Sidepn.setVisible(true);
					Orderpn.setVisible(false);
					ordertxt.setVisible(true);

					table.setVisible(false);
					order_bt.setVisible(true);
				}
			}

			private void doSide() {
				fr.add(Sidepn);
				JButton SideButton_김치 = new JButton(new ImageIcon("김치.jpg"));
				Sidepn.add(SideButton_김치);
				SideButton_김치.setBounds(20, 20, 259,194);
				// 메뉴이름 띄울 텍스트 필드 생성
				JTextField 김치 = new JTextField("김치  0원");
				Sidepn.add(김치);
				김치.setBounds(20,215,260,30);
				김치.setEditable(false);
				SideButton_김치.addActionListener(new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent e) {
						JButton srcBtn =(JButton)e.getSource();
						if(srcBtn==SideButton_김치) {
							int result=   JOptionPane.showConfirmDialog(SideButton_김치, "김치를 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
							if(result == JOptionPane.OK_OPTION) {
								ordertxt.append((MenuOrder.side.get(0).toString()));
								MenuOrder.order.add(MenuOrder.side.get(0));
								MenuOrder.new_Price(0);
							}

						}
					}
				});

				JButton SideButton_단무지 = new JButton("");
				SideButton_단무지.setIcon(new ImageIcon("단무지.jpg"));
				Sidepn.add(SideButton_단무지);
				SideButton_단무지.setBounds(280, 20, 259,194);

				// 메뉴이름 띄울 텍스트 필드 생성
				JTextField 단무지 = new JTextField("단무지  0원");
				Sidepn.add(단무지);
				단무지.setBounds(280,215,260,30);
				단무지.setEditable(false);
				SideButton_단무지.addActionListener(new ActionListener () {

					@Override
					public void actionPerformed(ActionEvent e) {
						JButton srcBtn =(JButton)e.getSource();
						if(srcBtn==SideButton_단무지) {
							int result=   JOptionPane.showConfirmDialog(SideButton_단무지, "단무지를 주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
							if(result == JOptionPane.OK_OPTION) {
								ordertxt.append((MenuOrder.side.get(1).toString()));
								MenuOrder.order.add(MenuOrder.side.get(1));
								MenuOrder.new_Price(0);
							}

						}
					}
				});

			}});
		// 메인화면 버튼 기능
		bt[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton srcBtn =(JButton)e.getSource();
				if(srcBtn==bt[4]) {

					Mealpn.setVisible(false);
					Drinkpn.setVisible(false);
					Snackpn.setVisible(false);
					Sidepn.setVisible(false);
					Orderpn.setVisible(false);
					ordertxt.setVisible(false);
					table.setVisible(true);
					order_bt.setVisible(false);
					logout.setVisible(true);
				}

			}


		});

		// 주문리스트 버튼 기능
		bt[5].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton srcBtn =(JButton)e.getSource();
				if(srcBtn==bt[5]) {
					fr.add(Orderpn);
					Mealpn.setVisible(false);
					Drinkpn.setVisible(false);
					Snackpn.setVisible(false);
					Sidepn.setVisible(false);
					Orderpn.setVisible(true);
					ordertxt.setVisible(false);
					logout.setVisible(true);

					table.setVisible(false);
					order_bt.setVisible(false);

					Orderpn.add(listtxt);
					Orderpn.setBounds(250, 140, 945, 460);
					Orderpn.setBackground(Color.white);


				}

			}



		});

		// 주문담기 버튼 추가 및 설정
		order_bt.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		order_bt.setBounds(895, 620, 300, 100);
		pn.add(order_bt); // 주문담기 버튼 생성 및 출력
		logout.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
		logout.setBounds(895, 620, 300, 100);
		pn.add(logout); //로그아웃 버튼 생성 및 출력
		order_bt.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				JButton srcBtn =(JButton)e.getSource();
				if(srcBtn==order_bt) {
					int result=JOptionPane.showConfirmDialog(order_bt, "주문하시겠습니까?", "주문확인",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(result==JOptionPane.OK_OPTION) {

						Custmoer_Order();
						// 주문담기 버튼을 누르면 주문내역, 총 주문비용을 모두 지우고 다시 주문문구 생성
						ordertxt.setText("");
						int sum = 0;
						listtxt.append(MenuOrder.total_Price(sum));
						ordertxt.append("<주문 목록> \n\n");
						MenuOrder.order.removeAllElements();
						MenuOrder.price.removeAllElements();

					}
				}

			}
			// 주문리스트에 저장할 내용을 설정


			private void Custmoer_Order() {

				Menu m;
				listtxt.append("\n------------------------모든 주문 리스트-------------------------\n");

				// 모든 주문리스트를 불러옴
				for(int i=0; i<MenuOrder.getNumOrders(); i++) {
					m=MenuOrder.getOrder(i);
					listtxt.append(i+1+". ");
					listtxt.append(m.toString());

				}

				listtxt.append("\n--------------------------------------------------------------------\n");
			}


		});

		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "pc12님 로그아웃 되었습니다 \n 메인화면으로 이동합니다", "로그아웃", JOptionPane.INFORMATION_MESSAGE);
				LoginView login = new LoginView();
				fr.setVisible(false);
			}
		});
		//로그아웃 버튼 이벤트

		// 첫 화면에는 주문기능 보이지 않게하기
		ordertxt.setVisible(false);
		order_bt.setVisible(false);


		// 프레임 설정
		fr.setContentPane(pn);
		fr.setSize(1300, 800);
		fr.setVisible(true);
		fr.setResizable(false);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void dispose() {
	}

}