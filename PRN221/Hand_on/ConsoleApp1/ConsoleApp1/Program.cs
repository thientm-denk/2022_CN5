using System;
using System.Collections.Generic;

namespace ConsoleApp1
{
    public interface Ins1
    {
        public void run();

    }
    public class Car 
    {
        public readonly int test = 15;
        public int x { get; set; }
        public int y { get; init; }
        public Car(int z)
        {
            this.x = z;
            this.y = z;

        }

        List<int> a = new List<int>();

        ArrayList

        
    }

    public class MiniCar : Car, Ins1
    {
        public int soxe
        {
            get
            {
                return soxe;
            }
            set
            {
                soxe = value;
            }
        }

        public MiniCar(int x,int soxe) : base(x)
        {
            this.soxe = soxe;
        }

    public void run()
    {
        throw new NotImplementedException();
    }
}



internal class Program
    {
        
    static void Main(string[] args)
        {
           

            //    Console.WriteLine("Hello World: ");

            //    int.TryParse(Console.ReadLine(), out int result);
            //    if(result is int count && count > 0)
            //    {
            //        Console.WriteLine("data valid");
            //    }
            //    else
            //    {
            //        Console.WriteLine("data invalid");
            //    }

            //    switch (result)
            //    {
            //        case int count2 when count2 > 0:
            //            {
            //                Console.WriteLine("data valid");
            //                break;
            //            }
            //        default:
            //            {
            //                Console.WriteLine("data invalid");
            //                break;

            //            }


            //    }
            int[] array1 = null;
            Console.WriteLine(array1?.Length.ToString() ?? "Array is empty");
        }
    }
}
