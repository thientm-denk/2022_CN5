using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AutoMobileLibrary.BussinessObject
{

    /// <summary>
    /// Thuộc tính của xe nằm ở đây
    /// </summary>
    public class Car
    {
        public int ID { get; set; }
        public string Name { get; set; }
        public string Manufactor { get; set; }
        public decimal price { get; set; }
        public int releaseYear { get; set; }

        public Car(int iD, string name, string manufactor, decimal price, int releaseYear)
        {
            ID = iD;
            Name = name;
            Manufactor = manufactor;
            this.price = price;
            this.releaseYear = releaseYear;
        }


    }
}
