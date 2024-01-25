
CFLAGS = -Wall -g
LDFLAGS := $(LDFLAGS)

SIG% : CFLAGS += -g
Pag7% : LDFLAGS += -pthread
%pth : LDFLAGS += -pthread

all : $(patsubst %.c,%,$(wildcard *.c))
PC_PLs_mc : LDFLAGS += -pthread

clean :
	-@rm -f core
	-@find . -type f -perm /0100 | xargs file /dev/null | grep ELF | cut -d: -f1 | xargs rm -vf
