
CFLAGS = -Wall
LDFLAGS := $(LDFLAGS)

SIG% : CFLAGS += -g
%pth : LDFLAGS += -pthread

all : $(patsubst %.c,%,$(wildcard *.c))

clean :
	-@rm -f core
	-@find . -type f -perm /0100 | xargs file /dev/null | grep ELF | cut -d: -f1 | xargs rm -vf
