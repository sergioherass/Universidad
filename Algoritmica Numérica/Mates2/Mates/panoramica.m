clc,clear
im1=imread('img1.jpg');
im2=imread('img2.jpg');
im3=imread('img3.jpg');
alto=850; 
ancho=1320;
pano = uint8(zeros(1000,3500,3));
pano(1:alto,1:ancho,:)=im1;
image(pano)
%control(im1,im2)
load datos1
xy=[Xi Yi];
uv=[Xd Yd];
P12=get_afin(xy,uv);
%fprintf('%6.3f %6.3f %7.0f\n',P12);
for y=1:900
    for x=1:3500
        if (y<=alto) && (x<=ancho)
            continue;
        else
            coord = [x y 1]*P12;
            x2=round(coord(1));
            y2=round(coord(2));
            if (y2>=1) && (y2<=alto) && (x2>=1) && (x2<=ancho)
                pano(y,x,:)=im2(y2,x2,:);
            end
        end
    end
end
image(pano)
% control(im2,im3)
load datos2
xy=[Xi Yi];
uv=[Xd Yd];
P23=get_afin(xy,uv);
%fprintf('%6.3f %6.3f %7.0f\n',P23);
P12_ext=[P12,[0 0 1]'];
P13=P12_ext*P23;
% Comprobamos que est� bien calculada la transformaci�n P13:
coord1=[2500 500 1]*P13;
% El resultado obtenido es coord1 = [488.6090, 470.8049]
coord2=[2500 500 1]*P12;
coord3=[coord2(1) coord2(2) 1]*P23;
% El resultado obtenido en este segundo caso es coord3 = [488.6090,
% 470.8049] Ambos resultados coinciden por lo que la transformaci�n est�
% bien hecha.
for y=1:900
    for x=1:3500
        if (y<=alto) && (x<=ancho)
            continue;
        else
            coord = [x y 1]*P12;
            x2=round(coord(1));
            y2=round(coord(2));
            if (y2>=1) && (y2<=alto) && (x2>=1) && (x2<=ancho)
                pano(y,x,:)=im2(y2,x2,:);
            else
                coord = [x y 1]*P13;
                x3=round(coord(1));
                y3=round(coord(2));
                if (y3>=1) && (y3<=alto) && (x3>=1) && (x3<=ancho)
                    pano(y,x,:)=im3(y3,x3,:);
                end
            end
        end
    end
end
image(pano)



