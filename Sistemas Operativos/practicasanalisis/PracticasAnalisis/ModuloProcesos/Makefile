
CFLAGS = -Wall
LDFLAGS := $(LDFLAGS)

SIG% : CFLAGS += -g
Pag7% : LDFLAGS += -pthread
%pth : LDFLAGS += -pthread


all : $(patsubst %.c,%,$(filter-out Tirada.c, $(wildcard *.c)))
Dados: Dados.o Tirada.o

clean :
	-@rm -f core
	-@rm -f *.o
	-@find . -type f -perm /0100 | xargs file /dev/null | grep ELF | cut -d: -f1 | xargs rm -vf
