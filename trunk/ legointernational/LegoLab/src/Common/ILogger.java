package Common;

public interface ILogger {
	public void log( int sample );
    public void log( Object objs[] );
	public void log( String string );
    //void appendLogStart(StringBuilder sb);
    //abstract void stream(StringBuilder sb);
	public abstract void close();
}
