namespace WindowsFormsApp1
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
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
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.GridContacts = new System.Windows.Forms.DataGridView();
            this.GridEmailAddr = new System.Windows.Forms.DataGridView();
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.GridContacts)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.GridEmailAddr)).BeginInit();
            this.SuspendLayout();
            // 
            // GridContacts
            // 
            this.GridContacts.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.GridContacts.Location = new System.Drawing.Point(34, 39);
            this.GridContacts.Name = "GridContacts";
            this.GridContacts.RowHeadersWidth = 51;
            this.GridContacts.Size = new System.Drawing.Size(630, 227);
            this.GridContacts.TabIndex = 0;
            // 
            // GridEmailAddr
            // 
            this.GridEmailAddr.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.GridEmailAddr.Location = new System.Drawing.Point(34, 326);
            this.GridEmailAddr.Name = "GridEmailAddr";
            this.GridEmailAddr.RowHeadersWidth = 51;
            this.GridEmailAddr.RowTemplate.Height = 24;
            this.GridEmailAddr.Size = new System.Drawing.Size(630, 188);
            this.GridEmailAddr.TabIndex = 1;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(826, 91);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(195, 63);
            this.button1.TabIndex = 2;
            this.button1.Text = "Connect";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(906, 408);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(185, 70);
            this.button2.TabIndex = 3;
            this.button2.Text = "Update DB";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1248, 704);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.GridEmailAddr);
            this.Controls.Add(this.GridContacts);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.GridContacts)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.GridEmailAddr)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView GridContacts;
        private System.Windows.Forms.DataGridView GridEmailAddr;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
    }
}

