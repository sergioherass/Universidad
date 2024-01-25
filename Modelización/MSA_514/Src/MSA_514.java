// MSA_514.java
// Multivariate Statistical Analysis of an 8-bit image series
// stored in a stack
// This plug-in was written by Gael Lalire and Benjamin Prouvost
// students of the Master 1 in Computer Science, University of Reims,
// under the supervision of Noel Bonnet (noel.bonnet@univ-reims.fr)
//  January 2006
//*******************************************************************

import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;
import javax.swing.*;
import java.text.*;

public class MSA_514 implements PlugInFilter {
  private ImagePlus imp_o,imp_n;
  private int taille_o,taille_n;
  private ImageStack stk_o,stk_n;

  public int setup(String args, ImagePlus imp) {
    if(args.equals("about")) {
      showAbout();
    }
    this.imp_o = imp; // imp contient la selection que ce soit une stack ou une image
    return DOES_8G+NO_UNDO+NO_CHANGES; // STACK_REQUIRED
  }

  public void run(ImageProcessor ip) {
    String[] methods = new String[] {"PCA","CA"};
    int choice_method;
    this.stk_o = this.imp_o.getStack();
    this.taille_o = this.stk_o.getSize();
    this.stk_n = this.imp_o.createEmptyStack();
    boolean cont = true;
    boolean console = false;
    do {
      GenericDialog gd = new GenericDialog("MSA");
      gd.addChoice("Variant : ", methods, methods[0]);
      gd.addNumericField("Number of factorial images : ", 1, 0);
      gd.addCheckbox("Display results", true);
      gd.showDialog();
      if(gd.wasCanceled()) return;
      this.taille_n = (int)gd.getNextNumber();
      console = gd.getNextBoolean();
      choice_method = gd.getNextChoiceIndex();
      cont = this.taille_n<=0;
      if(cont) IJ.showMessage("Error","Number of images must be greater than 0");
      else if(choice_method == 0) {
        cont = this.taille_n>this.taille_o;
        if(cont) IJ.showMessage("Error","The number of factorial images must be lower than or equal to the number of original images");
      }
      else if(choice_method == 1) {
        cont = this.taille_n>this.taille_o-1;
        if(cont) IJ.showMessage("Error","The number of factorial images must be lower than the number of original images");
      }
    } while(cont);

    String displ = "";
    if(choice_method==0) displ = Compute.PCA(this.stk_o,this.taille_n,this.stk_n);
    else displ = Compute.CA(this.stk_o,this.taille_n,this.stk_n);
    this.imp_n = new ImagePlus(methods[choice_method]+" ("+this.imp_o.getTitle()+")", this.stk_n);
    this.imp_n.show();
    if(console) {
      JFrame jf = new JFrame("Console "+methods[choice_method]+" ("+this.imp_o.getTitle()+")");
      JTextArea ta = new JTextArea();
      ta.setEditable(false);
      ta.setText(displ);
      jf.getContentPane().add(new JScrollPane(ta));
      jf.pack();
      jf.setVisible(true);
    }
  }
  
  public void showAbout() 
    {
    IJ.showMessage("MSA_514","Multivariate Statistical Analysis of an image series\n" +
    			"developed at the Inserm unit 514, Reims, France.\n" +
			"(http://www.univ-reims.fr/INSERM514)\n\n" +
    			"Works on a stack of 8-bit images.\n" +
      			"Two options are available :\n" +
      			"- Principal Components Analysis (without centering nor normalization)\n"+
      			"- Correspondence Analysis (double normalization on pixels and images)\n\n"+
			"A more detailed description of the plug-in can be found at:\n" +
			"http://www.univ-reims.fr/INSERM514/ImageJ\n");
    }
} // end of class

class Compute {
  private static DecimalFormat df = new DecimalFormat("0.0000"); // %f default
  private static String displ;
  private static ImageStack stk_o,stk_n;
  private static short[][] image; // UNSIGNED CHAR ?
  private static int taillex,tailley,taillez,taille2;   /* Dimension 1D and 2d */
  private static int NIMA;                              /* number of images */
  private static int nima2;                             /* square number of images */
  private static short[] choix = new short[10];         /* Choice of option */
  private static short[] axe0 = new short[3];           /* Flag for reconstitutions */
  private static double[] SI,SJ,S,VP,FI,PSI; 		
  private static double vptot,SITOT;
  private static int KVVP;                      /* Number of factorial images */
  private static boolean flagfi;		/* Indicates if FI was computed */
  private static short bufmin,bufmax,bufmoy;    /* Statistics for computed images */ 
  private static int nbits;			/* Number of bits of images */

  public static void PCA_calpsi() {
    double prod_scal,norme,norme2,moy1,moy2,sd1,sd2,cc;
    vptot-=1;
    for(int i=0; i<NIMA; i++) {
      if(SJ[i]<1.0e-6) {
        IJ.showMessage("Error","SJ("+i+") LOWER THAN 10**(-6) !!! arret de ImageJ");
        System.exit(-1); 	// why ?
      }
      else SJ[i]/=SITOT;
    }
    displ+="\n Image coordinates";
    displ+="\n------------------";
    df.applyPattern("0.000"); // "%.3f"
    for(int i=0; i<NIMA; i++) {
      displ+="\n     "+i+" \t ";
      for (int k=0; k<KVVP; k++) {
        PSI[i*KVVP+k]=S[i*NIMA+k];
        displ+=df.format(PSI[i*KVVP+k])+" \t ";
      }
    }
    df.applyPattern("0.0000"); // %f
    // Scalar product (just for tests 
    for(int k=0; k<KVVP; k++) {
      norme=0;
      moy1=0;
      for(int i=0; i<NIMA; i++) {
        moy1+=S[i*NIMA+k];
        norme+=S[i*NIMA+k]*S[i*NIMA+k];
      }
      moy1/=(double)NIMA;
      sd1=norme/NIMA-moy1*moy1;
      for(int j=k; j<KVVP; j++) {
        moy2=0;
        norme2=0;
        prod_scal=0;
        for(int i=0; i<NIMA; i++) {
          moy2+=S[i*NIMA+j];
          prod_scal+=S[i*NIMA+k]*S[i*NIMA+j];
          norme2+=S[i*NIMA+j]*S[i*NIMA+j];
        }
        moy2/=(double)NIMA;
        sd2=norme2/NIMA-moy2*moy2;
        cc=(prod_scal/NIMA-moy1*moy2)/Math.sqrt(sd1*sd2);
        // displ+="\n axe "+k+" avec axe "+j+"  Prod_scal= "+df.format(prod_scal)+" prod_scal_norme="+df.format((prod_scal/Math.sqrt(norme*norme2)))+" cc="+df.format(cc);
      }
    }
    ima_matcorr();
  }

  public static void CA_calpsi() {
    int i,k,l;
    /* Normalization of SJ */
    vptot-=1;
    for(i=0; i<NIMA; i++) {
      if(SJ[i]<1.0e-6) {
        IJ.showMessage("Error","SJ("+i+") LOWER THAN 10**(-6) !!! arret de ImageJ");
        System.exit(-1);
      }
      else {
        SJ[i]/=SITOT;
      }
    }
    displ+="\n Image coordinates";
    displ+="\n------------------";
    for(i=0; i<NIMA; i++) {
      displ+="\n     "+i+" \t ";
      for(k=0; k<KVVP; k++) {
        l=k+1;
        PSI[i*KVVP+k]=S[i*NIMA+l]/Math.sqrt((double)SJ[i])*Math.sqrt((double)VP[l]);
        displ+=df.format(PSI[i*KVVP+k])+" \t ";
      }
    }
  }

  public static void PCA_calfi() {
    // displ+="\n CALCUL DE FI";
    for(int k=0; k<KVVP; k++) {
      int l=k;
      int indice=k*taille2;
      for(int j=0; j<taille2; j++) {
        if(SI[j]!=0) {
          FI[indice+j] = 0.0;
          for(int i=0; i<NIMA; i++) FI[indice+j]+=(double)image[i][j]*S[i*NIMA+k];
        }
      }
    }
    flagfi=true;
  }

  public static void CA_calfi() {
    int i,j,k,l, indice;
    // displ+="\n CALCUL DE FI";
    for(k=0; k<KVVP; k++) {
      l=k+1;
      indice=k*taille2;
      for (j=0; j<taille2; j++) {
        if(SI[j]!=0) {
          FI[indice+j]=0.;
          for (i=0; i<NIMA; i++) 
	  	if(SJ[i]!=0) 
			FI[indice+j]+=(double)image[i][j]*S[i*NIMA+l]/Math.sqrt(SJ[i]);
          FI[indice+j]/=SI[j];
        }
      }
    }
    flagfi=true;
  }

  public static void PCA_project() {
    // char nom[30],nom2[30];
    double val,fimin,fimax,coef;
    int j,k,indice;
    short buffer[];  // WARNING: UNSIGNED

    buffer=new short[taille2];
    // Computes FI if it wasn't done before
    if(flagfi==false) PCA_calfi();
    displ+="\n PCA: Statistics of principal images (before conversion to 8 bits)";
    displ+="\n -----------------------------------------------------------------";
    for(k=0; k<KVVP; k++) {
      indice=k*taille2;
      /* Output on 8 bits (19/3/1992) */
      fimin=32767.;
      fimax=-32768.;
      for(j=0; j<taille2; j++) {
        val=FI[indice+j];
        if(val<fimin) fimin=val;
        if(val>fimax) fimax=val;
      }
      displ+="\n"+"Projection "+k+":";
      displ+=" \t min="+df.format(fimin)+" \t max="+df.format(fimax);
      coef=254./(fimax-fimin);
      for(j=0; j<taille2; j++) {
        val=(FI[indice+j]-fimin)*coef;
        short preca = (short)val;
        buffer[j]= (short)(preca%256);
      }
      ByteProcessor bpbuff = new ByteProcessor(taillex, tailley);
      for(int imx=0; imx<taillex; imx++) 
      	for(int imy=0; imy<tailley; imy++) 
		bpbuff.putPixel(imx, imy, buffer[imy*taillex+imx]);
      stk_n.addSlice("Projection "+k, bpbuff);
    }
  }

  public static void CA_project() {
    int j,k;
    double val,fimin,fimax,coef;
    int indice;
    short buffer[];

    buffer=new short[taille2];
    // Computes FI if it wasn't done previously
    if(flagfi==false) CA_calfi();
    displ+="\n CA: Statistics of factorial images (before conversion to 8 bits)";
    displ+="\n ----------------------------------------------------------------";
    for (k=0; k<KVVP; k++) {
      indice=k*taille2;
      displ+="\n"+"Projection "+k+":";
      /* Output on 8 bits (18/3/1993) */
      fimin=32767.;
      fimax=-32768.;
      for(j=0; j<taille2; j++) {
        val=FI[indice+j];
        if(val<fimin) fimin=val;
        if(val>fimax) fimax=val;
      }
      displ+=" \t min="+df.format(fimin)+" \t max="+df.format(fimax);
      coef=254./(fimax-fimin);
      for(j=0; j<taille2; j++) {
        val=(FI[indice+j]-fimin)*coef;
        short preca = (short)val;
        buffer[j]=(short)(preca%256);
      }
      ByteProcessor bpbuff = new ByteProcessor(taillex, tailley);
      for(int imx=0; imx<taillex; imx++) 
      	for(int imy=0; imy<tailley; imy++) 
		bpbuff.putPixel(imx, imy, buffer[imy*taillex+imx]);
      stk_n.addSlice("Projection "+k, bpbuff);
    }
  }

  public static void PCA_variance() {
    double x,y,val;
    
    // initialiZation
    for(int j=0; j<taille2; j++) SI[j]=0;
    //marges, matrice S et de sa trace
    for(int i=0; i<NIMA; i++) {
      SJ[i]=0;
      for(int j=0; j<taille2; j++) {
        x = image[i][j]; // peut etre j,i ???
        SI[j]+=x;
        SJ[i]+=x;
      }
    }
    displ+= "\n Matrix S :";
    displ+= "\n ----------";
    for(int i=0; i<NIMA; i++) {
      for(int l=0;l<=i;l++) {
        val = 0;
        for(int j=0; j<taille2; j++) {
          x=image[i][j];
          y=image[l][j];
          val+=x*y;
        }
        S[i*NIMA+l]=val;
      }
    }
    for(int i=0; i<NIMA; i++) for(int l=0;l<=i;l++) {
      S[i*NIMA+l]/=taille2;
      S[l*NIMA+i]=S[i*NIMA+l];
    }
    for(int i=0; i<NIMA; i++) {
      displ += "\n";
      for(int l=0; l<NIMA; l++) displ += " "+df.format(S[i*NIMA+l]) +" \t ";
    }
    vptot=0;
    for(int i=0; i<NIMA; i++) for(int l=0; l<NIMA; l++) if(i==l) vptot+=S[i*NIMA+l]; 
    SITOT = 0;
    displ += "\n Before diagonalization : TRACE ="+df.format(vptot);
    for(int i=0;i<NIMA;i++) SITOT+=SJ[i];
    // displ += "\n Sum of intensites="+df.format(SITOT);
  }
  
  public static void CA_variance() {
    int i,j,l;
    double x,y,sd,val;

    /* INITIALISATIONS */
    for(j=0; j<taille2; j++) SI[j]=0;

    /* CALCUL DES MARGES, DE LA MATRICE S ET DE SA TRACE */
    for(i=0; i<NIMA; i++) {
      SJ[i]=0;
      for(j=0; j<taille2; j++) {
        x=image[i][j];
        SI[j]+=x;
        SJ[i]+=x;
      }
    }
    displ+= "\n Matrix S :";
    displ+= "\n ----------";
    for(i=0; i<NIMA; i++) {
      for(l=0; l<=i; l++) {
        val=0;
        for(j=0; j<taille2; j++) {
          x=image[i][j];
          y=image[l][j];
          val+= (SI[j]!=0 ? x*y/SI[j] : 0);
        }
        S[i*NIMA+l]=val;
      }
    }
    for(i=0; i<NIMA; i++) {
      for (l=0; l<=i; l++) {
        sd=(double)SJ[i]*SJ[l];
        S[i*NIMA+l] /= Math.sqrt(sd);
        S[l*NIMA+i] = S[i*NIMA+l];
      }
    }
    for(i=0; i<NIMA; i++) {
      displ += "\n";
      for(l=0; l<NIMA; l++) displ += " "+df.format(S[i*NIMA+l])+" \t ";
    }
    vptot=0;
    for(i=0; i<NIMA; i++) for(l=0; l<NIMA; l++) if(i==l) vptot+=S[i*NIMA+l];
    displ += "\n Before diagonalization : TRACE ="+df.format(vptot);
    SITOT=0;
    for(i=0; i<NIMA; i++) SITOT+=SJ[i];
    // displ += "\n Sum of intensites="+df.format(SITOT);
  }

  public static void inverse() {
    // invert S
    // This is not "nice" programming, but ... it works
    double epsil,w2,w22,w2a,ww,wwa,ep,a,b,tteta,cteta,steta,ateta,flip;
    int ni,ki,k1,n1,kp,k,l;

    epsil=0.0000000001;
    w2=0.;
    for(l=0; l<NIMA; l++) for (k=0; k<NIMA; k++) w2+=S[l*NIMA+k]*S[l*NIMA+k];
    ep=epsil*w2/NIMA;
    ni=NIMA*(NIMA-1)/2;
    ki=ni;
    n1=NIMA-1;
    boucle_princ: while(ki>0) {
      for(k=0; k<n1; k++) {
        k1=k+1;
        boucle_inte: for(kp=k1; kp<NIMA; kp++) {
          w2=0.;
          ww=0.;
          for(l=0; l<NIMA; l++) {
            a=S[l*NIMA+k];
            b=S[l*NIMA+kp];
            w2+=a*b;
            ww+=(a+b)*(a-b);	
          }
          w22=w2*2.;
          w2a=Math.abs(w22);
          if(w2a>=ep || ww<0.) wwa=Math.abs(ww);
          else {
            ki--;
            if(ki<=0) break boucle_princ; // get out of 'while'
            else continue boucle_inte;
          }
          if(w2a<=wwa) {
            tteta=w2a/wwa;
            cteta=1./Math.sqrt(1.+tteta*tteta);
            steta=tteta*cteta;
          }
          else {
            ateta=wwa/w2a;
            steta=1./Math.sqrt(1.+ateta*ateta);
            cteta=ateta*steta;
          }
          cteta=Math.sqrt((1.+cteta)/2.);
          steta/=(2.*cteta);
          if(ww<0.) {
            flip=cteta;
            cteta=steta;
            steta=flip;
          }
          if(w22<0.) steta=-steta;
          for (l=0; l<NIMA; l++) {
            flip=S[l*NIMA+k];
            S[l*NIMA+k]=flip*cteta+S[l*NIMA+kp]*steta;
            S[l*NIMA+kp]=-flip*steta+S[l*NIMA+kp]*cteta;
          }
          ki=ni;
        }
      }
    }
    for(k=0; k<NIMA; k++) {
      VP[k]=0.;
      for(l=0; l<NIMA; l++) VP[k]+= S[l*NIMA+k]*S[l*NIMA+k];
      VP[k]=Math.sqrt(VP[k]);
    }
    for(k=0; k<NIMA; k++) for (l=0; l<NIMA; l++) S[l*NIMA+k]/=VP[k];
    vptot=0;
    for(k=0; k<NIMA; k++) vptot+=VP[k];
    displ+="\n After diagonalization : TRACE ="+ df.format(vptot);
  }

  public static void PCA_result() {
    double som;
    displ+="\nEigenvalue \t Log(Eigenvalue)      Percentage    Cumulated Percentage";
    displ+="\n---------------------------------------------------------------------";
    som=0;
    displ+="\n"+df.format(VP[0]);
    vptot-=VP[0];
    for(int k=0; k<KVVP-1; k++) {
      int l=k+1; 
      som+=VP[l]/vptot;
      displ+="\n"+df.format(VP[l])+" \t "+df.format(Math.log(VP[l]))+" \t "+df.format(VP[l]/vptot*100)+" \t "+df.format(som*100); 
    }
  }

  public static void CA_result() {
    int k,l;
    double som;
    displ+="\nEigenvalue  \t Log(Eigenvalue)      Percentage    Cumulated Percentage";
    displ+="\n----------------------------------------------------------------------";
    som=0;
    displ+="\n"+df.format(VP[0]);
    vptot-=1.;
    for(k=0; k<KVVP; k++) {
      l=k+1; 
      som+=VP[l]/vptot;
      displ+="\n"+df.format(VP[l])+"           "+df.format(Math.log(VP[l]))+"           "+df.format(VP[l]/vptot*100)+" \t "+df.format(som*100); 
    }
  }

  public static void ima_matcorr() {
    int i,j,k,m,n,indice;
    double Smin=1.e30,Smax=-1.e30,coef;
    short val; // ATTENTION unsigned
    short[] buffer = new short[65536]; // ATTENTION unsigned 

    for(i=0; i<NIMA*NIMA; i++) {
      if(S[i]<Smin) Smin=S[i];
      if(S[i]>Smax) Smax=S[i];
    }
    // displ+="\n Valeur mini de S ="+df.format(Smin)+"  Valeur maxi de S = "+df.format(Smax);

    coef=255./(Smax-Smin);
    k=256/NIMA;
    for(i=0; i<65536; i++) buffer[i]=0;
    for(j=0; j<NIMA; j++) {
      for (i=0; i<NIMA; i++) {
        // CAST UNSIGNED CHAR
        short preca = (short)((S[j*NIMA+i]-Smin)*coef);
        val= (short)(preca%256);
        for(m=0; m<k; m++) {
          indice=(j*k+m)*256+i*k;
          for(n=0; n<k; n++) buffer[indice+n]=val;
        }
      }
    }
  }

  public static String PCA(ImageStack astk_o, int nbaxes, ImageStack astk_n) {
    displ = "";
    stk_o = astk_o;
    stk_n = astk_n;
    // main
    flagfi = false;

    // fonction charge (ds l'ordre)
    NIMA = stk_o.getSize();
    image = new short[NIMA][];
    nima2=NIMA*NIMA;
    taillex = stk_o.getWidth();
    tailley = stk_o.getHeight();
    taillez = 1;
    taille2 = taillex*tailley*taillez;
    for(int i=0; i<NIMA; i++) {
      image[i] = new short[taille2];
      byte[] p = (byte [])(stk_o.getProcessor(i+1)).getPixels();
      for(int p_ind=0; p_ind<p.length; p_ind++) 
      	image[i][p_ind] = (short)((p[p_ind]+256)%256); // prend la partie >= 0
    }
    KVVP = nbaxes;
    if(KVVP>NIMA) KVVP=NIMA;

    // function alloc
    SI = new double[taille2];
    SJ = new double[NIMA];
    S = new double[nima2];
    FI = new double[KVVP*taille2];
    PSI = new double[NIMA*KVVP];
    VP = new double[NIMA];
	
    // method PCA
    PCA_variance();
    inverse();
    PCA_result();
    PCA_calpsi();
    PCA_project();
    return displ;
  }

  public static String CA(ImageStack astk_o, int nbaxes, ImageStack astk_n) {
    displ = "";
    stk_o = astk_o;
    stk_n = astk_n;
    // main
    flagfi = false;

    // fonction charge (ds l'ordre)
    NIMA = stk_o.getSize();
    image = new short[NIMA][];
    nima2=NIMA*NIMA;
    taillex = stk_o.getWidth();
    tailley = stk_o.getHeight();
    taillez = 1;
    taille2 = taillex*tailley*taillez;
    for(int i=0; i<NIMA; i++) {
      image[i] = new short[taille2];
      byte[] p = (byte [])(stk_o.getProcessor(i+1)).getPixels();
      for(int p_ind=0; p_ind<p.length; p_ind++) image[i][p_ind] = (short)((p[p_ind]+256)%256); // prend la partie >= 0
    }
    KVVP = nbaxes;
    if(KVVP>NIMA) KVVP=NIMA;

    // function alloc
    SI = new double[taille2];
    SJ = new double[NIMA];
    S = new double[nima2];
    FI = new double[KVVP*taille2];
    PSI = new double[NIMA*KVVP];
    VP = new double[NIMA];

    // method CA
    CA_variance();
    inverse();
    CA_result();
    CA_calpsi();
    CA_project();
    return displ;
  }
  
  
}



