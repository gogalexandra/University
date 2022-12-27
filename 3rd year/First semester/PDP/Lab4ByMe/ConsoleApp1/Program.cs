using System;
using System.Collections.Generic;
using Lab4.Implementation;

namespace Lab4
{
    class Program
    {
        static void Main()
        {
            var hosts = new List<string> {
                "www.cs.ubbcluj.ro/~rlupsa/edu/pdp/lab-4-futures-continuations.html"
            };

            //DirectCallback.Run(hosts);
            TasksMechanism.Run(hosts);
            //AsyncTasksMechanism.Run(hosts);
        }
    }
}
