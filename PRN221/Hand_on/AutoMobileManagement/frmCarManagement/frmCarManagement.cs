using AutoMobileLibrary.BussinessObject;
using AutoMobileLibrary.Repository;
using AutomobileWinApp;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace frmCarManagement
{
    
    public partial class frmCarManagement : Form
    {
        ICarRepository carRepository = new CarRepository();

        BindingSource source;
        public frmCarManagement()
        {
            InitializeComponent();
        }

        private void btnLoad_Click(object sender, EventArgs e)
        {
            loadCarList();
        }

        private void New_Click(object sender, EventArgs e)
        {
            frmCarDetails frmCarDetails = new frmCarDetails()
            {
                Text = "Add car",
                InsertOrUpdate = false,
                CarRepository = carRepository

            };
            if(frmCarDetails.ShowDialog() == DialogResult.OK)
            {
                loadCarList();
                source.Position = source.Count - 1;
            }
        }

        private void Delete_Click(object sender, EventArgs e)
        {
            try
            {
                var car = GetCarObject();
                carRepository.DeleteCar(car.ID);
                loadCarList();
            }catch(Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void Close_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void frmCarManagement_Load(object sender, EventArgs e)
        {
            Delete.Enabled = false;
            dataGridView1.CellDoubleClick += dataGridView1_CellDoubleClick ;
        }

        private void dataGridView1_CellDoubleClick(object sender, DataGridViewCellEventArgs e)
        {
            frmCarDetails frmCarDetails = new frmCarDetails
            {
                Text = "Update Car",
                InsertOrUpdate = true,
                CarInfo = GetCarObject(),
                CarRepository = carRepository
            };
            if(frmCarDetails.ShowDialog() == DialogResult.OK)
            {
                loadCarList();
                source.Position = source.Count - 1;
            }
        }

        private void ClearText()
        {
            txtCarId.Text = string.Empty;
            txtCarName.Text = string.Empty;
            txtManufactor.Text = string.Empty;
            txtPrice.Text = string.Empty;
            txtReleasedYear.Text = string.Empty;
        }

        private Car GetCarObject()
        {
            Car car = null;
            try
            {
                car = new Car(int.Parse(txtCarId.Text), txtCarName.Text, txtManufactor.Text, decimal.Parse(txtPrice.Text), int.Parse(txtReleasedYear.Text));
    
            }catch(Exception ex)
            {
                MessageBox.Show(ex.Message, "get car");
            }
            return car;
        }

        public void loadCarList()
        {
            var cars = carRepository.GetCars();
            try
            {
                source = new BindingSource();
                source.DataSource = cars;

                txtCarId.DataBindings.Clear();
                txtCarName.DataBindings.Clear();
                txtManufactor.DataBindings.Clear();
                txtPrice.DataBindings.Clear();
                txtReleasedYear.DataBindings.Clear();

                txtCarId.DataBindings.Add("Text", source, "ID");
                txtCarName.DataBindings.Add("Text", source, "Name");
                txtManufactor.DataBindings.Add("Text", source, "Manufactor");
                txtPrice.DataBindings.Add("Text", source, "price");
                txtReleasedYear.DataBindings.Add("Text", source, "releaseYear");

                dataGridView1.DataSource = null;
                dataGridView1.DataSource = source;
                if(cars.Count() == 0)
                {
                    ClearText();
                    Delete.Enabled = false;
                }
                else
                {
                    Delete.Enabled = true;
                }
            }catch(Exception ex)
            {
                MessageBox.Show(ex.ToString());
            }
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }

}
