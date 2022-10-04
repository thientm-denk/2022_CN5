using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WinFormsApp1
{
    public partial class frm1 : Form
    {
        public frm1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            for (int i = 1; i < 32; i++)
            {
                comboBox2.Items.Add(i.ToString());
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            txtNhapLieu.Text = "";
            
        }

        private void hienthi_Click(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            txtNhapLieu.Text = "Hello World!!";
          
        }

        private void View_Click(object sender, EventArgs e)
        {
            

        }

        private void txtNhapLieu_TextChanged(object sender, EventArgs e)
        {

        }

        private void button3_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void button4_Click(object sender, EventArgs e)
        {

            hienthi.Text = mutiText.Text;
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            if(mutiText.Text.Trim() == "")
            {
                button4.Enabled = false;
            }
            else
            {
                button4.Enabled = true;
            }
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void radioButton2_CheckedChanged(object sender, EventArgs e)
        {
            ketQua.Text = "Đói đó má ơi";
        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void radioButton1_CheckedChanged(object sender, EventArgs e)
        {
            ketQua.Text = "Đi làm sales đi má ơi";
        }

        private void radioButton3_CheckedChanged(object sender, EventArgs e)
        {
            ketQua.Text = "Chuyeển Ngành";
        }

        private void groupBox2_Enter(object sender, EventArgs e)
        {

        }

        private void checkBox1_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void checkBox3_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void button5_Click(object sender, EventArgs e)
        {
            String tmp = "";
            if (checkBox1.Checked)
            {
                tmp += checkBox1.Text + ", ";
            }
            if (checkBox2.Checked)
            {
                tmp += checkBox2.Text + ", ";
            }
            if (checkBox3.Checked)
            {
                tmp += checkBox3.Text + ", ";
            }
            if (checkBox4.Checked)
            {
                tmp += checkBox4.Text + ", ";
            }
            tmp = tmp.Substring(0, tmp.Length - 2);
            label1.Text = "Món ăn bạ thích là: " + tmp;
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void datesubmit_Click(object sender, EventArgs e)
            
        {
            int valuee = int.Parse(comboBox2.Text);
            if (valuee >= 1 && valuee <= 20) {
                MessageBox.Show("Ahihi nguoi gia");
            }
            else
            {
                MessageBox.Show("Ban that tre dep");
            }
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {

        }
    }
}
