namespace WinFormsApp1
{
    partial class frm1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.txtNhapLieu = new System.Windows.Forms.TextBox();
            this.hienthi = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.button3 = new System.Windows.Forms.Button();
            this.mutiText = new System.Windows.Forms.TextBox();
            this.button4 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // txtNhapLieu
            // 
            this.txtNhapLieu.Location = new System.Drawing.Point(285, 148);
            this.txtNhapLieu.Name = "txtNhapLieu";
            this.txtNhapLieu.Size = new System.Drawing.Size(366, 27);
            this.txtNhapLieu.TabIndex = 0;
            this.txtNhapLieu.TextChanged += new System.EventHandler(this.txtNhapLieu_TextChanged);
            // 
            // hienthi
            // 
            this.hienthi.AutoSize = true;
            this.hienthi.Location = new System.Drawing.Point(370, 314);
            this.hienthi.Name = "hienthi";
            this.hienthi.Size = new System.Drawing.Size(30, 20);
            this.hienthi.TabIndex = 1;
            this.hienthi.Text = "xxx";
            this.hienthi.Click += new System.EventHandler(this.hienthi_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(285, 203);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(83, 40);
            this.button1.TabIndex = 2;
            this.button1.Text = "Show";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(434, 203);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(83, 40);
            this.button2.TabIndex = 2;
            this.button2.Text = "Clear";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(568, 203);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(83, 40);
            this.button3.TabIndex = 2;
            this.button3.Text = "Close";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // mutiText
            // 
            this.mutiText.Location = new System.Drawing.Point(93, 65);
            this.mutiText.Multiline = true;
            this.mutiText.Name = "mutiText";
            this.mutiText.Size = new System.Drawing.Size(137, 257);
            this.mutiText.TabIndex = 3;
            this.mutiText.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // button4
            // 
            this.button4.Location = new System.Drawing.Point(247, 314);
            this.button4.Name = "button4";
            this.button4.Size = new System.Drawing.Size(95, 90);
            this.button4.TabIndex = 4;
            this.button4.Text = "View";
            this.button4.UseVisualStyleBackColor = true;
            this.button4.Click += new System.EventHandler(this.button4_Click);
            // 
            // frm1
            // 
            this.AcceptButton = this.button1;
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.CancelButton = this.button3;
            this.ClientSize = new System.Drawing.Size(972, 540);
            this.Controls.Add(this.button4);
            this.Controls.Add(this.mutiText);
            this.Controls.Add(this.button3);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.hienthi);
            this.Controls.Add(this.txtNhapLieu);
            this.Name = "frm1";
            this.Text = "Login";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox txtNhapLieu;
        private System.Windows.Forms.Label hienthi;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.TextBox mutiText;
        private System.Windows.Forms.Button button4;
    }
}
