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

namespace Try1
{
    public partial class Form1: Form
    {
        private SqlConnection connection = new SqlConnection(GetConnectionString());
        private SqlDataAdapter contactTable;
        private DataSet dataSetContacts;
        private SqlCommandBuilder cmdbl;
        public Form1()
        {
            InitializeComponent();
            dataGridViewContacts.SelectionChanged += new EventHandler(LoadChildren);
            LoadParent();
        }

        private void LoadParent()
        {
            SqlDataAdapter contactsTable = new SqlDataAdapter("select * from Contacts", GetConnectionString());
            DataSet dataSet = new DataSet();

            contactsTable.Fill(dataSet, "Contacts");
            dataGridViewContacts.DataSource = dataSet.Tables["Contacts"];
        }

        private void LoadChildren(object sender, EventArgs e)
        {
            LoadChildren();
        }

        private void LoadChildren()
        {
            int contactId = Convert.ToInt32(dataGridViewContacts.CurrentRow.Cells[0].Value);

            SqlCommand command = new SqlCommand("SELECT * FROM Email_address WHERE contact_id=@id")
            {
                Connection = new SqlConnection(GetConnectionString())
            };
            command.Parameters.AddWithValue("@id", contactId);

            contactTable = new SqlDataAdapter(command);
            dataSetContacts = new DataSet();

            contactTable.Fill(dataSetContacts, "Email_address");
            dataGridViewEmailAddr.DataSource = dataSetContacts.Tables["Email_address"];
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            // TODO: This line of code loads data into the 'plannerDataSet2.Email_address' table. You can move, or remove it, as needed.
            this.email_addressTableAdapter.Fill(this.plannerDataSet2.Email_address);
            // TODO: This line of code loads data into the 'plannerDataSet1.Contact_history' table. You can move, or remove it, as needed.
            this.contact_historyTableAdapter.Fill(this.plannerDataSet1.Contact_history);
            // TODO: This line of code loads data into the 'plannerDataSet.Contacts' table. You can move, or remove it, as needed.
            this.contactsTableAdapter.Fill(this.plannerDataSet.Contacts);

        }
        private static string GetConnectionString()
        {
            return "Data Source=DESKTOP-P9HV7VF;" +
                "Initial Catalog=Planner;" +
                "Integrated Security=True";
        }

        private void button1_Click(object sender, EventArgs e)
        {

        }

        private void dataGridViewContacts_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            try
            {
                int contactId = Convert.ToInt32(dataGridViewContacts.CurrentRow.Cells[0].Value);

                SqlCommand command = new SqlCommand("DELETE FROM Email_address WHERE contact_id=@id")
                {
                    Connection = new SqlConnection(GetConnectionString())
                };
                command.Parameters.AddWithValue("@id", contactId);

                contactTable = new SqlDataAdapter(command);
                dataSetContacts = new DataSet();

                contactTable.Fill(dataSetContacts, "Email_address");
                dataGridViewEmailAddr.DataSource = dataSetContacts.Tables["Email_address"];
                MessageBox.Show("Deleted!");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message.ToString());
                connection.Close();
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            try
            {
                int contactId = Convert.ToInt32(dataGridViewContacts.CurrentRow.Cells[0].Value);

                int idGiven = Int32.Parse(IdTextBoxAdd.Text.ToString());
                string emailGiven = AddrTextBoxAdd.Text.ToString();


                SqlCommand command = new SqlCommand("INSERT INTO Email_address(id, contact_id, email_address) VALUES (@idGiven, @id, @emailGiven)")
                {
                    Connection = new SqlConnection(GetConnectionString())
                };
                command.Parameters.AddWithValue("@id", contactId);
                command.Parameters.AddWithValue("@idGiven", idGiven);
                command.Parameters.AddWithValue("@emailGiven", emailGiven);

                contactTable = new SqlDataAdapter(command);
                dataSetContacts = new DataSet();

                contactTable.Fill(dataSetContacts, "Email_address");
                dataGridViewEmailAddr.DataSource = dataSetContacts.Tables["Email_address"];
                MessageBox.Show("Added!");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message.ToString());
                connection.Close();
            }
        }

        private void button1_Click_1(object sender, EventArgs e)
        {
            try
            {
                int contactId = Convert.ToInt32(dataGridViewContacts.CurrentRow.Cells[0].Value);

                int idGiven = Int32.Parse(IdTextBoxUpdate.Text.ToString());
                string emailGiven = AddrTextBoxUpdate.Text.ToString();


                SqlCommand command = new SqlCommand("UPDATE Email_address SET email_address = @emailGiven WHERE contact_id = @id; ")
                {
                    Connection = new SqlConnection(GetConnectionString())
                };
                command.Parameters.AddWithValue("@id", contactId);
                command.Parameters.AddWithValue("@idGiven", idGiven);
                command.Parameters.AddWithValue("@emailGiven", emailGiven);

                contactTable = new SqlDataAdapter(command);
                dataSetContacts = new DataSet();

                contactTable.Fill(dataSetContacts, "Email_address");
                dataGridViewEmailAddr.DataSource = dataSetContacts.Tables["Email_address"];
                MessageBox.Show("Updated!");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message.ToString());
                connection.Close();
            }
        }
    }
}
