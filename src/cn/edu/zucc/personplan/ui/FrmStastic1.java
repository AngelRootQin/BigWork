package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.PersonPlanUtil;

public class FrmStastic1 extends JDialog {
	
	DefaultTableModel tablmod=new DefaultTableModel();
	JTable dataTable= new JTable(tablmod);
	private Object tblPlanTitle[] = BeanPlan.tableTitles;
	private Object tblPlanData[][];
	List<BeanPlan> allPlan = null;
	void reloadPlanTable() {
		try {
			allPlan = PersonPlanUtil.planManager.loadfinish1();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPlanData = new Object[allPlan.size()][BeanPlan.tableTitles.length];
		for (int i = 0; i < allPlan.size(); i++) {
			tblPlanData[i][0] = i + 1;
			for (int j = 1; j < BeanPlan.tableTitles.length; j++)
				tblPlanData[i][j] = allPlan.get(i).getCell(j);
		}
		tablmod.setDataVector(tblPlanData,tblPlanTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}

	public FrmStastic1(Frame f, String s, boolean b) {
		super(f, s, b);
		//提取现有数据
		this.reloadPlanTable();
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.validate();
		this.dataTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmStastic1.this.dataTable.getSelectedRow();
				if (i < 0) {
					return;
				}
				JFrame JF = new JFrame();
				BeanPlan plan = allPlan.get(i);
				FrmListstep dlg =  new FrmListstep(JF,plan.getPlanname()+"超时完成步骤统计", true, plan);
				dlg.setVisible(true);
			}
		});
	}
}