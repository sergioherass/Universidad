
CFLAGS = -Wall
LDFLAGS := $(LDFLAGS)

all : $(patsubst %.c,%,$(wildcard *.c))

Bib% : CFLAGS  += -shared
%_th Thread Volcado_mem Listado_personal : LDFLAGS += -pthread
%_est : LDFLAGS += -static
Carga_% Navegador : LDLIBS += -ldl
Usa_% : LDLIBS += -lm

clean :
	-@rm -f core .mapnew .mapold
	-@find . -type f -perm /0100 | xargs file /dev/null | grep ELF | cut -d: -f1 | xargs rm -vf
