#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
void power(float x, int n)
/// This function has as parameters a base and a power which have to be computed. Result is set to 1 and k (the number of times the base
/// has been multipled) to 0. As long as k is smaller than the power, there are 2 cases inside the loop: if the power is odd then the
/// result is only multiplied with the base, else with itself and k is doubled up. The function prints on the output the result

{
	float result = 1;
	int k = 0;
	while (k < n) {
		if (k == n - 1) {
			result = result * x;
			k += 1;
		}
		else {
			if (result == 1) {
				result = x;
				k += 1;
			}
			else {
				result = result * result;
				k *= 2;
			}
		}

	}
	printf("\nThe result is: %f \n", result);
}

void longest_seq(int array[100], int dimension) {
/// Having the array and the dimension as parameters, this function goes throw the elements of the vectors and checks the condition:
/// consecutive elementes having diffrent signs. Local variable current_length computes the length of the subsequence that contains 
/// the current elem (array[i]) that respects the condition. If the sequence is over (an elem does not respect the cond) it is compare with 
/// the length of longest subseq of this kind and saved if greater. The longest subseq is printed on the output
	int i, max_length = 0, current_length = 1, index_max_seq = 0;
	for (i = 0; i < dimension - 1; i++) {
		if (array[i] > 0 && array[i + 1] < 0 || array[i] < 0 && array[i + 1] > 0) {
			current_length += 1;
		}
		else {
			if (current_length > max_length) {
				max_length = current_length;
				index_max_seq = i;
			}
			current_length = 1;
		}
	}
	if (current_length > max_length) {
		max_length = current_length;
		index_max_seq = i;
	}

	printf("Longest sequence is: ");
	for (i = index_max_seq - max_length + 1; i <= index_max_seq; i++) {
		printf("%d ", array[i]);
	}

}

int gcd(int number1, int number2) {
	/// number1, number2 are 2 integers numbers
	/// the function uses Euclid algorithm for finding the greates common divisor (substracs from the bigger number, the smaller one until they 
	/// are equal). When the numbers are equal in each one of them the gcd is stored and is the value is 1 than the number are relatively prime
	/// one of the numbers is return 
	while (number1 != number2) {
		if (number1 > number2) {
			number1 -= number2;
		}
		else {
			number2 -= number1;
		}
	}
	//return number1;
}

void option_3(int number) {
	/// number: is the number given by the user
	/// Using a while loop we search throw all numbers smaller than the parameter number and check if that nr is relatively prime to the given one
	/// if so, the number is added into an array to be printed when the while loop is over.
	int array[100] = { 0 }, aux = number - 1, i = 0;
	while (aux > 0) {
		if (gcd(aux, number) == 1){
			array[i] = aux;
			i += 1;
		}
		aux -= 1;
	}
	i -= 1;
	printf("The numbers are: ");
	for (i; i >= 0; i--) {
		printf("%d ", array[i]);
	}
}

void print_menu() {
///This function prints on the output the choices that the user has
	printf("\n 0.Exit");
	printf("\n 1.Calculate the power of a number");
	printf("\n 2.Find the longest contiguous subsequence such that any two consecutive elements have contrary signs");
	printf("\n 3.All numbers that are smaller and relatively prime to n");
	return;
}
int main() {
	float x;
	int n, ok = 0, command, dimension, i, array[100];
	while (ok != 1) {
		print_menu();
		printf("\n Give a command: ");
		scanf("%d", &command);
		if (command == 0) {
			printf("Bye Bye!");
			ok = 1;
		}
		else {
			if (command == 1) {
				printf("\nGive a number: ");
				scanf("%f", &x);
				printf("\nGive a power for the number: ");
				scanf("%d", &n);
				power(x, n);
			}
			else {
				if (command == 2) {
					printf("\nGive the dimension of the vector: ");
					scanf("%d", &dimension);
					printf("\nGive the vector: ");
					for (i = 0; i < dimension; i++)  {
						scanf("%d", &array[i]);
					}
					longest_seq(array, dimension);
				}
				else {
					if (command == 3) {
						printf("\nGive a natural number: ");
						scanf("%d", &n);
						option_3(n);
					}
					else {
						printf("\nWrong command!!!!");
					}
			
				}

			}
		}
	}

	return 0;
}