using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {

        SqlConnection connection;
        SqlDataAdapter dataAdapterContacts, dataAdapterEmailAddr;
        DataSet dataSet;
        SqlCommandBuilder commandBuilder;
        BindingSource bindingSourceContacts, bindingSourceEmailAddr;

        private void button1_Click(object sender, EventArgs e)
        {
            connection = new SqlConnection(@"Data Source=Desktop-p9hv7vf;Initial Catalog=Planner;Integrated Security=True");
            dataSet = new DataSet();
            dataAdapterEmailAddr = new SqlDataAdapter("SELECT * FROM Email_address", connection);
            dataAdapterContacts = new SqlDataAdapter("SELECT * FROM Contacts", connection);
            

            commandBuilder = new SqlCommandBuilder(dataAdapterEmailAddr);
            
            dataAdapterEmailAddr.Fill(dataSet, "Email_address");
            dataAdapterContacts.Fill(dataSet, "Contacts");

            DataRelation dataRelation = new DataRelation("FK__Email_add__conta__29572725", dataSet.Tables["Contacts"].Columns["id"],
                dataSet.Tables["Email_address"].Columns["contact_id"]);
            dataSet.Relations.Add(dataRelation);

            bindingSourceEmailAddr = new BindingSource();
            bindingSourceContacts = new BindingSource(); 
            
            bindingSourceContacts.DataSource = dataSet;
            bindingSourceContacts.DataMember = "Contacts";

            bindingSourceEmailAddr.DataSource = bindingSourceContacts;
            bindingSourceEmailAddr.DataMember = "FK__Email_add__conta__29572725";
           
            GridContacts.DataSource = bindingSourceContacts;
            GridEmailAddr.DataSource = bindingSourceEmailAddr;


        }

        private void button2_Click(object sender, EventArgs e)
        {
            dataAdapterEmailAddr.Update(dataSet, "Email_address");
        }

        public Form1()
        {
            InitializeComponent();
        }
    }
}
