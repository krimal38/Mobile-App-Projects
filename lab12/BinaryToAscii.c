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

  fp_in = fopen(argv[1], "rb");

  if (fp_in == NULL) {
    printf("\nCannot open file %s\n", argv[1]);
    exit(1);
  }

  fp_out = fopen(argv[2], "w");

  for (i=0; i<10; i++) {
    fread(&num_float, sizeof(num_float), 1, fp_in);
    fprintf(fp_out, "%.4lf\n", num_float);
  }

  for (i=0; i<10; i++) {
    fread(buff, sizeof(buff), 1, fp_in);
    fputs(buff, fp_out);
  }

  for (i=0; i<10; i++) {
    fread(&num_int, sizeof(num_int), 1, fp_in);
    fprintf(fp_out, "%d\n", num_int);
  }

  fclose(fp_in);
  fclose(fp_out);

  return 0;
}
