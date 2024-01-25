function demo2d(arg)

global txt p traza 
%global f_ptr

if nargin==0, arg='1'; end

switch(arg)
  
    % GRAFICOS DE FUNCIONES  
    
  case '1'
       
    t=linspace(0,2*pi,2000); 
    R1=4; x1=-4+R1*cos(t); y1=-2 + R1*sin(t);
    R2=6; x2= 3+R2*cos(t); y2= 1 + R2*sin(t);
   
    p=zeros(1,2);
    p(1)=plot(x1,y1,'m','LineWidth',2); hold on; 
    p(2)=plot(x2,y2,'c','LineWidth',2); hold off    
    
    %f_ptr=@fun1;
    demo2d inicio
 
  case '2'
   
    t=linspace(0,2*pi,2000); 
    R1=4; R2=6; R3=2.5; %sqrt(9);   
    x1=-4+R1*cos(t); y1=-2 + R1*sin(t);
    x2= 3+R2*cos(t); y2= 1 + R2*sin(t);
    x3=-4+R3*cos(t); y3= 4 + R3*sin(t);  
  
    p=zeros(1,3);
    p(1)=plot(x1,y1,'m','LineWidth',2); hold on; 
    p(2)=plot(x2,y2,'c','LineWidth',2);   
    p(3)=plot(x3,y3,'LineWidth',2,'Color',[1 0.5 0]); hold off    
    
    %f_ptr=@fun3;
    demo2d inicio   
    

  case '2old'
   
    t=linspace(0,2*pi,500); 
 
     R1=6; x1= 3+R1*cos(t); y1= 1 + R1*sin(t);
     a=1.5;
     x2=3*a*cos(t)-a*cos(3*t); 
     y2=3*a*sin(t)-a*sin(3*t);  
          
     p=zeros(1,2);
     p(1)=plot(x1,y1,'c','LineWidth',2); hold on;
     p(2)=plot(x2,y2,'m','LineWidth',2);
     
     %f_ptr=@fun2;
     demo2d inicio
  
        
 case 'inicio'
     
  set(gcf,'Pos',[200 100 550 600]); %,'Menubar','none');
  set(gcf,'Name','Ecuaciones no lineales 2D (ATG)');
  set(gca,'Units','Norm','Pos',[0.05 0.10 0.9 0.85]);
  set(gca,'Xlim',10*[-1 1],'Ylim',10*[-1 1]);    

  set(gca,'ButtonDownFcn','demo2d start_iter');
  set(p,'ButtonDownFcn','demo2d start_iter');
 
  set(gcf,'WindowButtonMotionFcn','demo2d pos');

  %[s fs]=wavread('succ.wav');    

  txt(1)=uicontrol('Style','Text','Units','Norm','Pos',[0.05 0.01 0.90 0.04],...
                   'FontSize',12,'FontWeight','Bold','HorizontalAlig','left',...
                   'FontName','Monospaced','FontWeight','Bold');
  
  txt(2)=uicontrol('Style','Text','Units','Norm','Pos',[0.07  0.11 0.70 0.04],...
                   'FontSize',12,'FontWeight','Bold','FontName','Monospaced',...
                   'BackgroundColor',[1 1 1],'HorizontalAlig','left');
 
   uicontrol('Style','PushButton','Units','Norm','Pos',[0.80  0.01 0.15 0.04],...
             'FontSize',12,'FontWeight','Bold','HorizontalAlig','left',...
             'FontName','Monospaced','String','Clear',...
             'Callback','demo2d clear');            
            
   hold on; traza=plot(NaN,NaN); hold off
   
            
  case 'pos'
    pos = get(gca,'CurrentPoint'); pos = pos(1,1:2)';        
    msg=sprintf('Posición cursor: (%+6.2f,%+6.2f)',pos);
    set(txt(2),'String',msg);
    
  case 'start_iter'

    set(txt(1),'ForegroundColor','k');
    set(traza,'Color','k','LineWidth',1);   
        
    pos = get(gca,'CurrentPoint'); 
    X0 = pos(1,1:2)';
          
    hold on;  
    traza=plot(X0(1),X0(2),'ko:');
    set(traza,'MarkerSize',2,'MarkerFaceColor','k','tag','trazas');
    hold off
    
    iteracion
        
    col='r';
    set(txt(1),'ForegroundColor',col);
    set(traza,'Color',col,'LineWidth',1,'ButtonDownFcn','demo2d start_iter');

   
  case 'clear'
        
   delete(findobj(gcf,'tag','trazas'))   
   hold on; traza=plot(NaN,NaN); hold off
     
 end

end




