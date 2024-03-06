package drawing;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class DlgHexagon extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCenterX;
	private JTextField txtCenterY;
	private JTextField txtRadius;
	private Color interCol;
	private Color exterCol;
	private boolean ok;
	private JButton btnInteriorColor; 
	private JButton btnExteriorColor;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgHexagon dialog = new DlgHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgHexagon() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Hexagon");
		contentPanel.setBackground(Color.cyan);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblCenterX = new JLabel("CenterX:");
		JLabel lblCenterY = new JLabel("CenterY:");
		JLabel lblRadius = new JLabel("Radius:");
		txtCenterX = new JTextField();
		txtCenterX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar(); //sve sto kucamo na tastaturi
				if(t=='-') {
					e.consume(); //ne ispise taj znak
					getToolkit().beep(); //zvuk
				}
			}
		});
		txtCenterX.setText("");
		txtCenterX.setColumns(10);
		txtCenterY = new JTextField();
		txtCenterY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				if(t=='-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtCenterY.setText("");
		txtCenterY.setColumns(10);
		txtRadius = new JTextField();
		txtRadius.setText("");
		txtRadius.setColumns(10);
		
		btnInteriorColor = new JButton("Interior color");
		btnInteriorColor.setBackground(interCol);
		btnInteriorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interCol=JColorChooser.showDialog(null, "Choose a inner color!", null);
			    btnInteriorColor.setBackground(interCol);
			}
		});
		
		btnExteriorColor = new JButton("Exterior color");
		btnExteriorColor.setBackground(exterCol);
		btnExteriorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exterCol=JColorChooser.showDialog(null, "Choose a outer color!", null);
			    btnExteriorColor.setBackground(exterCol);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCenterX)
								.addComponent(lblCenterY)
								.addComponent(lblRadius))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCenterY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCenterX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnInteriorColor)
						.addComponent(btnExteriorColor))
					.addContainerGap(277, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCenterX)
						.addComponent(txtCenterX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCenterY)
						.addComponent(txtCenterY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnInteriorColor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExteriorColor)
					.addContainerGap(88, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try
						{
						setOk(true);
						dispose();
						
						}
						catch(NumberFormatException ex)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greska", JOptionPane.WARNING_MESSAGE);
							
						} catch (Exception e1) {
							
							JOptionPane.showMessageDialog(new JFrame(), "Poluprecnik mora da bude pozitivan!", "Greska", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void setTxtCenterXEditable(boolean b) {
		txtCenterX.setEditable(b);
	}
	public void setTxtCenterYEditable(boolean b) {
		txtCenterY.setEditable(b);
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getTxtCenterX() {
		return txtCenterX.getText();
	}

	public void setTxtCenterX(String txtCenterX) {
		this.txtCenterX.setText(txtCenterX);
	}
	
	public String getTxtCenterY() {
		return txtCenterY.getText();
	}

	public void setTxtCenterY(String txtCenterY) {
		this.txtCenterY.setText(txtCenterY);
	}
	public String getTxtRadius() {
		return txtRadius.getText();
	}

	public void setTxtRadius(String txtRadius) {
		this.txtRadius.setText(txtRadius);
	}

	public Color getInterCol() {
		return interCol;
	}

	public void setInterCol(Color inerCol) {
		this.interCol = inerCol;
	}

	public Color getExterCol() {
		return exterCol;
	}

	public void setExterCol(Color exterCol) {
		this.exterCol = exterCol;
	}
	public JButton getBtnInteriorColor() {
		return btnInteriorColor;
	}

	public void setBtnInteriorColor(JButton btnInteriorColor) {
		this.btnInteriorColor = btnInteriorColor;
	}

	public JButton getBtnExteriorColor() {
		return btnExteriorColor;
	}

	public void setBtnExteriorColor(JButton btnExteriorColor) {
		this.btnExteriorColor = btnExteriorColor;
	}
}
