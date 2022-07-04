package polymorphism;


public class SonySpeaker implements Speaker {

    public SonySpeaker() {
        System.out.println("===> SonySpeaker 객체 생성");
    }
    @Override
    public void volumeUP() {
        System.out.println("SonySpeaker ---소리 올린다.");
    }
    public void volumeDowm() {
        System.out.println("SonySpeaker ---소리 내린다.");
    }


}
