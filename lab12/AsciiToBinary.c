#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
  char buff[40];
  FILE* fp_in;
  FILE* fp_out;
  int i, num_int;
  double num_float;

  if (argc != 3) {
    printf("\n%s <input_file> <output_file> \n", argv[0]);
    exit(1);
  }

  fp_in = fopen(argv[1], "r");

  if (fp_in == NULL) {
    printf("\nCannot open file %s\n", argv[1]);
    exit(1);
  }

  fp_out = fopen(argv[2], "wb");

  for (i=0; i<10; i++) {
    fscanf(fp_in, "%lf", &num_float);
    fwrite(&num_float, sizeof(num_float), 1, fp_out);
  }

  // read the new line after the last number
  fgets(buff, 40, fp_in);

  for (i=0; i<10; i++) {
    fgets(buff, sizeof(buff), fp_in);
    fwrite(buff, sizeof(buff), 1, fp_out);
  }

  for (i=0; i<10; i++) {
    fscanf(fp_in, "%d", &num_int);
    fwrite(&num_int, sizeof(num_int), 1, fp_out);
  }

  fclose(fp_in);
  fclose(fp_out);

  return 0;
}
