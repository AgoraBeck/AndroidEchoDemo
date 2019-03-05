## ʹ�� android �Դ���������

Android ��4.1 API leve 16 ����˻����������� **AcousticEchoCanceler**

���������ֻ�����̫�࣬��Ƭ�����أ�ʵ��Ч��Ҳ��һ��������ֻ��������ʹ��

��¼һ�²��裺

**���ϵͳ�Ƿ�֧�ֻ�������**

**����¼�ƣ����õ�sessionid**

**������������ ����¼�Ƶ�sessionid**

**�������ţ�����¼�Ƶ�sessionid**

��������¼�Ƶ�sessionid ���������ˣ����ܹ�ʵ�����mic�ɼ����������Ȳ��ų��������ˡ�

�����룺
```
    public static boolean chkNewDev()
    {
          return android.os.Build.VERSION.SDK_INT >= 16;
    }
    
	public static boolean isDeviceSupport()
	{
        return AcousticEchoCanceler.isAvailable();
	}
```
����Ƿ�֧�֣���Ȼ���ڲ�׼ȷ�������
```
int InitAudioRecord()
		{
			m_bufferSizeInBytes = AudioRecord.getMinBufferSize(m_sampleRateInHz,  m_channelConfig, m_audioFormat);  	
			if (chkNewDev())
			{
				m_audioRecord = new AudioRecord(MediaRecorder.AudioSource.VOICE_COMMUNICATION, m_sampleRateInHz, m_channelConfig, m_audioFormat, m_bufferSizeInBytes);
			}
			else
			{
				m_audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, m_sampleRateInHz, m_channelConfig, m_audioFormat, m_bufferSizeInBytes);
			}
			
			int packSize = Math.min(960, m_bufferSizeInBytes*2);
			audioData = new short[packSize];
			return 0;
		}
```
���ϵͳ֧�ֻ�������������**MediaRecorder.AudioSource.VOICE_COMMUNICATION**
```
public int GetSessionId()
		{
			return m_audioRecord.getAudioSessionId();
		}
```
��ȡmic��sessionid�������������Ͳ��Ź�����
```
public boolean initAEC(int audioSession)
	{
		if (m_canceler != null)
		{
			return false;
		}
		m_canceler = AcousticEchoCanceler.create(audioSession);
		m_canceler.setEnabled(true);
		return m_canceler.getEnabled();
	}
```
������������ ��ʹ��
```
		int InitAudioTrack()
		{
			int m_sampleRateInHz = 48000;  
			int m_channelConfig  =  AudioFormat.CHANNEL_IN_STEREO;   
			int m_audioFormat    = AudioFormat.ENCODING_PCM_16BIT; 
			int m_bufferSizeInBytes = 1024*4;
			
			if (chkNewDev() && m_recorder != null)
			{
				m_audioTrack = new AudioTrack(AudioManager.STREAM_VOICE_CALL, m_sampleRateInHz, m_channelConfig, m_audioFormat, m_bufferSizeInBytes, AudioTrack.MODE_STREAM, m_recorder.GetSessionId());
			}
			else
			{
				m_audioTrack = new AudioTrack(AudioManager.STREAM_VOICE_CALL, m_sampleRateInHz, m_channelConfig, m_audioFormat, m_bufferSizeInBytes, AudioTrack.MODE_STREAM);
			}
			return 0;
		}
```
���ϵͳ֧�ֻ������� �򴴽�����mic ��sessionid

ok����ͨ��

```
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
        audioManager.setSpeakerphoneOn(true);
```

����˵���ڳ���������ʱ����Ҫ����һ�� **AudioManager.MODE_IN_COMMUNICATION** �� �������֤��������Ч��