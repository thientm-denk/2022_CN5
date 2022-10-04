using AutoMobileLibrary.Repository;
using AutoMobileLibrary.BussinessObject;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AutomobileWinApp
{
    public partial class frmCarDetails : Form
    {
        // cai dat form
        public frmCarDetails()
        {
            InitializeComponent();
        }

        public ICarRepository CarRepository { get; set; }
        public bool InsertOrUpdate { get; set; } //false: insert. true: update
        public Car CarInfo { get; set; }
        // Load khi mo form'
        private void frmCarDetails_Load(object sender, EventArgs e)
        {
            cboManufacturer.SelectedIndex = 0;
            txtCarID.Enabled = !InsertOrUpdate;
            if(InsertOrUpdate == true)
            {
                txtCarID.Text = CarInfo.ID.ToString();
                txtCarName.Text = CarInfo.Name;
                txtPrice.Text = CarInfo.price.ToString();
                txtRelelaseYear.Text = CarInfo.releaseYear.ToString();
                cboManufacturer.Text = CarInfo.Manufactor.Trim();
            }
        }

       

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                var car = new Car(int.Parse(txtCarID.Text), txtCarName.Text, cboManufacturer.Text, decimal.Parse(txtPrice.Text), int.Parse(txtRelelaseYear.Text));

                if (InsertOrUpdate == false)
                {
                    CarRepository.InsertCar(car);
                }
                else
                {
                    CarRepository.UpdateCar(car);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, InsertOrUpdate == false ? "Add new car" : "Update a car");
            
            }
        }


        private void button2_Click(object sender, EventArgs e)
        {
            Close();
        }

       
    }
}
