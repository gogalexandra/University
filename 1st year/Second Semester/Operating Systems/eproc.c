#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>


int main(){
	srand(getpid());
	int r0 = mkfifo("p2c", 0600);
	int r1 = mkfifo("c2p", 0600);
	if(r0 != 0 || r1 != 0){
		printf("FIFO cracked\n");
		exit(1);
	}

	int pid = fork();

	if(pid < 0){
		printf("Process could not be created\n");
		exit(1);
	}
	
	if(pid == 0){
		int p2c = open("p2c", O_RDONLY);
		int c2p = open("c2p", O_WRONLY);
		int n;
		int s = read(p2c, &n, sizeof(int));
		if(s < 0){
			printf("Error writing to fifo\n");
			exit(1);
		}
		char string[n+1];
		s = read(p2c, string, n+1);
		
		if(s != n+1){
			printf("Error writing to fifo\n");
			exit(1);
		}
		int i;
		for(i = 0 ; i <= n ; i++){
			int x = rand()%25;
			string[i] += x;
		}
		s = write(c2p, string, n+1);
		
		if(s < 0){
			printf("Error writing to fifo\n");
			exit(1);
		}
		close(p2c);
		close(c2p);
		exit(0);
	}
	int p2c = open("p2c", O_WRONLY);
	int c2p = open("c2p", O_RDONLY);
	int n = rand()%21;
	n += 10;
	char string[n + 1];
	int i;
	for(i = 0 ; i < n ; i++){
		string[i] = 'a';
	}
	string[n] = 0;
	int s = write(p2c, &n, sizeof(int));
	if(s < 0){
		printf("Error writing to fifo\n");
		exit(1);
	}

	s = write(p2c, string, n+1);

	if(s < 0){
		printf("Error writing to fifo\n");
		exit(1);
	}
	wait(0);	
	///gata copilu
	
	s = read(c2p, string, n+1);
	if(s != n+1){
		printf("Error in plm!\n");
		exit(1);
	}

	printf("N: %d\n", n);
	printf("string: %s\n", string);
	close(p2c);
	close(c2p);
	remove("p2c");
	remove("c2p");
	return 0;
}
