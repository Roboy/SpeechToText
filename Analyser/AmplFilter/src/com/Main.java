package com.roboy;

import java.io.*;
import java.math.BigInteger;

public class Main
{
    public static void main(String[] args)
    {
        byte t = 0;
        int i = 0, j = 0, d = 0;
        long n = 0;
        int smpl_seq, bitsPerSample = 0;//16 - bits per sample => 32 bits is enough (8bits*4)
        long subchunk2Size = 0;
        int threshold = 30000;//max = 65535
        try( FileInputStream instream=new FileInputStream("C://Users//Bratik//Desktop//Voice004mod.wav");
             FileOutputStream outstream=new FileOutputStream("C://Users//Bratik//Desktop//Voice004mod-1.wav")/**/ )
        {
            byte[] buffer = new byte[instream.available()];
//            byte[] buffer_1 = new byte[outstream.available()];
//            outstream.read(buffer_1, 0, buffer_1.length);
            instream.read(buffer, 0, buffer.length);
            bitsPerSample += buffer[35]<0 ? (int)(256+buffer[35]) : (int)buffer[35];
            bitsPerSample = bitsPerSample << 8;
            bitsPerSample = buffer[34]<0 ? (int)(256+buffer[34]) : (int)buffer[34];
            for (i=30, t=0; i< 500 && t==0; i++)
                if ((char)buffer[i]=='d' && (char)buffer[i+1]=='a' && (char)buffer[i+2]=='t' && (char)buffer[i+3]=='a')
                {
                    t=1;
                    d = i;
                }
            for (i=d+7; i>d+4; i--)
            {
                subchunk2Size += buffer[i]<0 ? (long)(256+buffer[i]) : (long)buffer[i];
                //System.out.println(Long.toBinaryString(buffer[i]<0 ? (long)(256+buffer[i]) : (long)buffer[i]));
                subchunk2Size = subchunk2Size << 8;
            }
            subchunk2Size += buffer[i]<0 ? (long)(256+buffer[i]) : (long)buffer[i];
            //System.out.println(Long.toBinaryString(buffer[i]<0 ? (long)(256+buffer[i]) : (long)buffer[i]));
            n = (long)(subchunk2Size*8/bitsPerSample);
            //System.out.println("subchunk: "+subchunk2Size);
            //System.out.println("data bytes: "+n);
            //System.out.println("bytes all: "+buffer.length);
            //System.out.println("ha-ha: "+buffer[317537357]);
/*            for (i=44; i<50; i++)
                System.out.print(Integer.toBinaryString((short)buffer[i])+" ");
            for (i=218103801; i<218103807; i++)
                System.out.print(Integer.toBinaryString((short)buffer[i])+" ");
            System.out.println();
            for (i=218103808; i<218103814; i++)
                System.out.print(Integer.toBinaryString((short)buffer[i])+" ");
            for (i=317537351; i<317537357; i++)
                System.out.print(Integer.toBinaryString((short)buffer[i])+" ");*/

            for (i=d+8; i<buffer.length; )
            {
                //System.out.println(i);
                //sample transformation
                smpl_seq = 0;
                smpl_seq += buffer[i+1]<0 ? (int)(256+buffer[i+1]) : (int)buffer[i+1];
                smpl_seq = smpl_seq << 8;
                smpl_seq += buffer[i]<0 ? (int)(256+buffer[i]) : (int)buffer[i];
                //System.out.println(Integer.toBinaryString(smpl_seq));
                if (smpl_seq < threshold)
                {
                    //smpl_seq = 0;
                    buffer[i] = 0;
                    buffer[i + 1] = 0;
                }
                //System.out.println(Integer.toBinaryString(smpl_seq));
                //-----------------------------------
                i += 2;
            }
/*            for (i=0, t = 1; i<500 && t==1; i++)
            {
                if (buffer[i]!=buffer_1[i])
                    t = 0;
            }
            System.out.println("header ?= "+t+"length ?= "+(buffer.length==buffer_1.length));*/
            outstream.write(buffer, 0, buffer.length);/**/
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
