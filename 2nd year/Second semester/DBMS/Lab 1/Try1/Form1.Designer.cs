namespace Try1
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
            this.components = new System.ComponentModel.Container();
            this.dataGridViewContacts = new System.Windows.Forms.DataGridView();
            this.idDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.first_name = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.last_name = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.phone_number = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.contactsBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.plannerDataSet = new Try1.PlannerDataSet();
            this.contactsTableAdapter = new Try1.PlannerDataSetTableAdapters.ContactsTableAdapter();
            this.dataGridViewEmailAddr = new System.Windows.Forms.DataGridView();
            this.idDataGridViewTextBoxColumn1 = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.email_address = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.contactidDataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.emailaddressBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.plannerDataSet2 = new Try1.PlannerDataSet2();
            this.contacthistoryBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.plannerDataSet1 = new Try1.PlannerDataSet1();
            this.contact_historyTableAdapter = new Try1.PlannerDataSet1TableAdapters.Contact_historyTableAdapter();
            this.IdTextBoxAdd = new System.Windows.Forms.TextBox();
            this.AddrTextBoxAdd = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.button3 = new System.Windows.Forms.Button();
            this.email_addressTableAdapter = new Try1.PlannerDataSet2TableAdapters.Email_addressTableAdapter();
            this.label3 = new System.Windows.Forms.Label();
            this.AddrTextBoxUpdate = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.IdTextBoxUpdate = new System.Windows.Forms.TextBox();
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewContacts)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.contactsBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.plannerDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewEmailAddr)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.emailaddressBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.plannerDataSet2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.contacthistoryBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.plannerDataSet1)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridViewContacts
            // 
            this.dataGridViewContacts.AutoGenerateColumns = false;
            this.dataGridViewContacts.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewContacts.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.idDataGridViewTextBoxColumn,
            this.first_name,
            this.last_name,
            this.phone_number});
            this.dataGridViewContacts.DataSource = this.contactsBindingSource;
            this.dataGridViewContacts.Location = new System.Drawing.Point(12, 57);
            this.dataGridViewContacts.Name = "dataGridViewContacts";
            this.dataGridViewContacts.RowHeadersWidth = 51;
            this.dataGridViewContacts.RowTemplate.Height = 24;
            this.dataGridViewContacts.Size = new System.Drawing.Size(556, 154);
            this.dataGridViewContacts.TabIndex = 0;
            this.dataGridViewContacts.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridViewContacts_CellContentClick);
            // 
            // idDataGridViewTextBoxColumn
            // 
            this.idDataGridViewTextBoxColumn.DataPropertyName = "id";
            this.idDataGridViewTextBoxColumn.HeaderText = "id";
            this.idDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.idDataGridViewTextBoxColumn.Name = "idDataGridViewTextBoxColumn";
            this.idDataGridViewTextBoxColumn.Width = 125;
            // 
            // first_name
            // 
            this.first_name.DataPropertyName = "first_name";
            this.first_name.HeaderText = "first_name";
            this.first_name.MinimumWidth = 6;
            this.first_name.Name = "first_name";
            this.first_name.Width = 125;
            // 
            // last_name
            // 
            this.last_name.DataPropertyName = "last_name";
            this.last_name.HeaderText = "last_name";
            this.last_name.MinimumWidth = 6;
            this.last_name.Name = "last_name";
            this.last_name.Width = 125;
            // 
            // phone_number
            // 
            this.phone_number.DataPropertyName = "phone_number";
            this.phone_number.HeaderText = "phone_number";
            this.phone_number.MinimumWidth = 6;
            this.phone_number.Name = "phone_number";
            this.phone_number.Width = 125;
            // 
            // contactsBindingSource
            // 
            this.contactsBindingSource.DataMember = "Contacts";
            this.contactsBindingSource.DataSource = this.plannerDataSet;
            // 
            // plannerDataSet
            // 
            this.plannerDataSet.DataSetName = "PlannerDataSet";
            this.plannerDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // contactsTableAdapter
            // 
            this.contactsTableAdapter.ClearBeforeFill = true;
            // 
            // dataGridViewEmailAddr
            // 
            this.dataGridViewEmailAddr.AutoGenerateColumns = false;
            this.dataGridViewEmailAddr.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewEmailAddr.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.idDataGridViewTextBoxColumn1,
            this.email_address,
            this.contactidDataGridViewTextBoxColumn});
            this.dataGridViewEmailAddr.DataSource = this.emailaddressBindingSource;
            this.dataGridViewEmailAddr.Location = new System.Drawing.Point(581, 57);
            this.dataGridViewEmailAddr.Name = "dataGridViewEmailAddr";
            this.dataGridViewEmailAddr.RowHeadersWidth = 51;
            this.dataGridViewEmailAddr.RowTemplate.Height = 24;
            this.dataGridViewEmailAddr.Size = new System.Drawing.Size(705, 154);
            this.dataGridViewEmailAddr.TabIndex = 2;
            // 
            // idDataGridViewTextBoxColumn1
            // 
            this.idDataGridViewTextBoxColumn1.DataPropertyName = "id";
            this.idDataGridViewTextBoxColumn1.HeaderText = "id";
            this.idDataGridViewTextBoxColumn1.MinimumWidth = 6;
            this.idDataGridViewTextBoxColumn1.Name = "idDataGridViewTextBoxColumn1";
            this.idDataGridViewTextBoxColumn1.Width = 125;
            // 
            // email_address
            // 
            this.email_address.DataPropertyName = "email_address";
            this.email_address.HeaderText = "email_address";
            this.email_address.MinimumWidth = 6;
            this.email_address.Name = "email_address";
            this.email_address.Width = 125;
            // 
            // contactidDataGridViewTextBoxColumn
            // 
            this.contactidDataGridViewTextBoxColumn.DataPropertyName = "contact_id";
            this.contactidDataGridViewTextBoxColumn.HeaderText = "contact_id";
            this.contactidDataGridViewTextBoxColumn.MinimumWidth = 6;
            this.contactidDataGridViewTextBoxColumn.Name = "contactidDataGridViewTextBoxColumn";
            this.contactidDataGridViewTextBoxColumn.Width = 125;
            // 
            // emailaddressBindingSource
            // 
            this.emailaddressBindingSource.DataMember = "Email_address";
            this.emailaddressBindingSource.DataSource = this.plannerDataSet2;
            // 
            // plannerDataSet2
            // 
            this.plannerDataSet2.DataSetName = "PlannerDataSet2";
            this.plannerDataSet2.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // contacthistoryBindingSource
            // 
            this.contacthistoryBindingSource.DataMember = "Contact_history";
            this.contacthistoryBindingSource.DataSource = this.plannerDataSet1;
            // 
            // plannerDataSet1
            // 
            this.plannerDataSet1.DataSetName = "PlannerDataSet1";
            this.plannerDataSet1.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // contact_historyTableAdapter
            // 
            this.contact_historyTableAdapter.ClearBeforeFill = true;
            // 
            // IdTextBoxAdd
            // 
            this.IdTextBoxAdd.Location = new System.Drawing.Point(206, 304);
            this.IdTextBoxAdd.Name = "IdTextBoxAdd";
            this.IdTextBoxAdd.Size = new System.Drawing.Size(119, 22);
            this.IdTextBoxAdd.TabIndex = 5;
            // 
            // AddrTextBoxAdd
            // 
            this.AddrTextBoxAdd.Location = new System.Drawing.Point(206, 345);
            this.AddrTextBoxAdd.Name = "AddrTextBoxAdd";
            this.AddrTextBoxAdd.Size = new System.Drawing.Size(119, 22);
            this.AddrTextBoxAdd.TabIndex = 6;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(112, 304);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(18, 16);
            this.label1.TabIndex = 8;
            this.label1.Text = "Id";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(36, 345);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(94, 16);
            this.label2.TabIndex = 9;
            this.label2.Text = "Email address";
            this.label2.Click += new System.EventHandler(this.label2_Click);
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(231, 407);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(94, 32);
            this.button3.TabIndex = 15;
            this.button3.Text = "Add";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // email_addressTableAdapter
            // 
            this.email_addressTableAdapter.ClearBeforeFill = true;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(527, 304);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(18, 16);
            this.label3.TabIndex = 16;
            this.label3.Text = "Id";
            // 
            // AddrTextBoxUpdate
            // 
            this.AddrTextBoxUpdate.Location = new System.Drawing.Point(620, 339);
            this.AddrTextBoxUpdate.Name = "AddrTextBoxUpdate";
            this.AddrTextBoxUpdate.Size = new System.Drawing.Size(121, 22);
            this.AddrTextBoxUpdate.TabIndex = 17;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(451, 345);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(94, 16);
            this.label4.TabIndex = 18;
            this.label4.Text = "Email address";
            // 
            // IdTextBoxUpdate
            // 
            this.IdTextBoxUpdate.Location = new System.Drawing.Point(620, 298);
            this.IdTextBoxUpdate.Name = "IdTextBoxUpdate";
            this.IdTextBoxUpdate.Size = new System.Drawing.Size(121, 22);
            this.IdTextBoxUpdate.TabIndex = 19;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(645, 407);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(96, 32);
            this.button1.TabIndex = 20;
            this.button1.Text = "Update";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click_1);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(936, 407);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(93, 32);
            this.button2.TabIndex = 21;
            this.button2.Text = "Delete";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(12, 24);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(106, 16);
            this.label5.TabIndex = 22;
            this.label5.Text = "Contacts(Parent)";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(578, 24);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(148, 16);
            this.label6.TabIndex = 23;
            this.label6.Text = "Email Addresses(Child)";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1298, 490);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.IdTextBoxUpdate);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.AddrTextBoxUpdate);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.button3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.AddrTextBoxAdd);
            this.Controls.Add(this.IdTextBoxAdd);
            this.Controls.Add(this.dataGridViewEmailAddr);
            this.Controls.Add(this.dataGridViewContacts);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewContacts)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.contactsBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.plannerDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewEmailAddr)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.emailaddressBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.plannerDataSet2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.contacthistoryBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.plannerDataSet1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridViewContacts;
        private PlannerDataSet plannerDataSet;
        private System.Windows.Forms.BindingSource contactsBindingSource;
        private PlannerDataSetTableAdapters.ContactsTableAdapter contactsTableAdapter;
        private System.Windows.Forms.DataGridView dataGridViewEmailAddr;
        private PlannerDataSet1 plannerDataSet1;
        private System.Windows.Forms.BindingSource contacthistoryBindingSource;
        private PlannerDataSet1TableAdapters.Contact_historyTableAdapter contact_historyTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn idDataGridViewTextBoxColumn;
        private System.Windows.Forms.DataGridViewTextBoxColumn first_name;
        private System.Windows.Forms.DataGridViewTextBoxColumn last_name;
        private System.Windows.Forms.DataGridViewTextBoxColumn phone_number;
        private System.Windows.Forms.TextBox IdTextBoxAdd;
        private System.Windows.Forms.TextBox AddrTextBoxAdd;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button button3;
        private PlannerDataSet2 plannerDataSet2;
        private System.Windows.Forms.BindingSource emailaddressBindingSource;
        private PlannerDataSet2TableAdapters.Email_addressTableAdapter email_addressTableAdapter;
        private System.Windows.Forms.DataGridViewTextBoxColumn idDataGridViewTextBoxColumn1;
        private System.Windows.Forms.DataGridViewTextBoxColumn email_address;
        private System.Windows.Forms.DataGridViewTextBoxColumn contactidDataGridViewTextBoxColumn;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox AddrTextBoxUpdate;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox IdTextBoxUpdate;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
    }
}

