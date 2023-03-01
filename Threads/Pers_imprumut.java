package Threads;

import Service.Service_CartiCititori;

import java.util.concurrent.locks.Lock;

public class Pers_imprumut extends Thread{
    private Imprumuta_returneaza imprumutaReturneaza;
    private Service_CartiCititori serviceCartiCititori;
    private Lock locker;

    public Pers_imprumut(Imprumuta_returneaza imprumutaReturneaza, Lock locker, Service_CartiCititori serviceCartiCititori)
    {
        this.imprumutaReturneaza = imprumutaReturneaza;
        this.locker = locker;
        this.serviceCartiCititori = serviceCartiCititori;
    }

    @Override
    public void run() {

        while (serviceCartiCititori.getNr_carti_imprumutate() != 0 && serviceCartiCititori.getNr_carti_libere() != 0)
        {
            try
            {
                locker.lock();
                imprumutaReturneaza.imprumuta_carte();
            }
            catch (RuntimeException e)
            {
                e.printStackTrace();
            }
            finally
            {
                locker.unlock();
            }

            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }
}
