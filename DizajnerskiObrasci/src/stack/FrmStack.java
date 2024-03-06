//package stack;
//
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.DefaultListModel;
//import javax.swing.GroupLayout;
//import javax.swing.GroupLayout.Alignment;
//import javax.swing.LayoutStyle.ComponentPlacement;
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import java.awt.SystemColor;
//import javax.swing.JScrollPane;
//import javax.swing.JList;
//import javax.swing.JOptionPane;
//
//public class FrmStack extends JFrame {
//
//	private JPanel contentPane;
//	DefaultListModel<Rectangle> dlm = new DefaultListModel<Rectangle>();
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrmStack frame = new FrmStack();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public FrmStack() {
//		setTitle("IT46-2017 Dragan Krsmanovic");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		setSize(743,533);
//		contentPane = new JPanel();
//		contentPane.setBackground(SystemColor.activeCaption);
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		
//		JPanel South = new JPanel();
//		
//		JPanel Center = new JPanel();
//		GroupLayout gl_contentPane = new GroupLayout(contentPane);
//		gl_contentPane.setHorizontalGroup(
//			gl_contentPane.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
//						.addComponent(Center, GroupLayout.PREFERRED_SIZE, 714, GroupLayout.PREFERRED_SIZE)
//						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
//							.addGap(3)
//							.addComponent(South, 0, 0, Short.MAX_VALUE)))
//					.addContainerGap())
//		);
//		gl_contentPane.setVerticalGroup(
//			gl_contentPane.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_contentPane.createSequentialGroup()
//					.addComponent(Center, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
//					.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
//					.addComponent(South, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
//		);
//		
//		JScrollPane scrlRect = new JScrollPane();
//		GroupLayout gl_Center = new GroupLayout(Center);
//		gl_Center.setHorizontalGroup(
//			gl_Center.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_Center.createSequentialGroup()
//					.addContainerGap()
//					.addComponent(scrlRect, GroupLayout.PREFERRED_SIZE, 692, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap(12, Short.MAX_VALUE))
//		);
//		gl_Center.setVerticalGroup(
//			gl_Center.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_Center.createSequentialGroup()
//					.addContainerGap()
//					.addComponent(scrlRect, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//		);
//		
//		JList lstRect = new JList();
//		scrlRect.setViewportView(lstRect);
//		lstRect.setModel(dlm);
//		Center.setLayout(gl_Center);
//		
//		JButton btnAdd = new JButton("Add");
//		btnAdd.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				DlgRectangle dlg= new DlgRectangle();
//				dlg.setVisible(true);
//				if(dlg.isCommited()){
//					try {
//					int x=Integer.parseInt(dlg.getTxtUpperLeftX().getText());
//					int y= Integer.parseInt(dlg.getTxtUpperLeftY().getText());
//					Point p = new Point(x,y);
//					int width=Integer.parseInt(dlg.getTxtWidth().getText());
//					int height= Integer.parseInt(dlg.getTxtHeight().getText());
//					Rectangle r = new Rectangle(p,width,height);
//					
//					dlm.add(0, r);
//				}catch(Exception ex) {
//					JOptionPane.showMessageDialog(new JPanel(), "Error", "Error", JOptionPane.ERROR_MESSAGE);
//				}
//				}
//			}
//		});
//		
//		JButton btnDelete = new JButton("Delete");
//		btnDelete.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				try {
//				Rectangle r = dlm.getElementAt(0);
//				DlgRectangle dlg = new DlgRectangle();	
//				dlg.getTxtUpperLeftX().setText(Integer.toString(r.getUpperLeft().getX()));
//				dlg.getTxtUpperLeftY().setText(Integer.toString(r.getUpperLeft().getY()));
//				dlg.getTxtWidth().setText(Integer.toString(r.getWidth()));
//				dlg.getTxtHeight().setText(Integer.toString(r.getHeight()));
//				dlg.getTxtUpperLeftX().setEnabled(false);
//				dlg.getTxtUpperLeftY().setEnabled(false);
//				dlg.getTxtWidth().setEnabled(false);
//				dlg.getTxtHeight().setEnabled(false);
//				dlg.setVisible(true);
//
//				if(dlg.isCommited()) {
//				int replay = JOptionPane.showConfirmDialog(new JPanel(), "Are you sure?", "Delete", JOptionPane.YES_NO_OPTION);
//				if(replay == JOptionPane.YES_OPTION) {
//					dlm.removeElement(r);
//				}
//				}
//				}catch(IndexOutOfBoundsException ex) {
//					JOptionPane.showMessageDialog(new JPanel(), "List is empty!", "Empty list", JOptionPane.ERROR_MESSAGE);
//				}
//				
//				
//			}
//		});
//		GroupLayout gl_South = new GroupLayout(South);
//		gl_South.setHorizontalGroup(
//			gl_South.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_South.createSequentialGroup()
//					.addGap(51)
//					.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
//					.addGap(48)
//					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
//					.addContainerGap(431, Short.MAX_VALUE))
//		);
//		gl_South.setVerticalGroup(
//			gl_South.createParallelGroup(Alignment.LEADING)
//				.addGroup(gl_South.createSequentialGroup()
//					.addContainerGap()
//					.addGroup(gl_South.createParallelGroup(Alignment.LEADING)
//						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
//						.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
//					.addContainerGap())
//		);
//		South.setLayout(gl_South);
//		contentPane.setLayout(gl_contentPane);
//	}
//}
