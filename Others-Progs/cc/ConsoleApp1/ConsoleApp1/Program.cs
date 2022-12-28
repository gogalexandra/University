

using System;

class TestClass
{
    static void Main(string[] args)
    {
        int[] array = { 12, 23, -23, -12, 13 };
        int dim = array.Length;
        int[] positiveNr = new int[dim];
        int index1 = 0;
        int[] negativeNr = new int[dim];
        int index2 = 0;
        for (int i = 0; i < dim; i++)
        {
            if (array[i] > 0)
            {
                positiveNr[index1] = array[i];
                index1++;
            }
            else
            {
                negativeNr[index2] = array[i];
                index2++;
            }
        }
    }
}

Console.WriteLine("Array of pos nr");
for (int i = 0; i < index1; i++) {
    Console.WriteLine($"{positiveNr[i]}");
}
Console.WriteLine("Array of neg nr");
for (int i = 0; i < index2; i++)
{
    Console.WriteLine($"{negativeNr[i]}");
}