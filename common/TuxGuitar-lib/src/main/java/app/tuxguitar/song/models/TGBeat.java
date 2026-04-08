/*
 * Created on 23-nov-2005
 */
package app.tuxguitar.song.models;

import app.tuxguitar.song.factory.TGFactory;

/**
 *
 * @author julian
 */
public abstract class TGBeat implements Comparable<TGBeat> {

	public static final int MAX_VOICES = 2;

	/*
	 * doc 1
	 */
	private long start;
	private Long preciseStart;
	private TGMeasure measure;
	private TGChord chord;
	private TGText text;
	private TGVoice[] voices;
	private TGStroke stroke;
	private TGPickStroke pickStroke;

	/*
	 * doc 1
	 * @param TGFactory
	 */
	public TGBeat(TGFactory factory) {
		this.preciseStart = TGDuration.getPreciseStartingPoint();
		this.start = TGDuration.getStartingPoint();
		this.stroke = factory.newStroke();
		this.pickStroke = factory.newPickStroke();
		this.voices = new TGVoice[ MAX_VOICES ];
		for( int i = 0 ; i < MAX_VOICES ; i ++ ){
			this.setVoice(i, factory.newVoice(i));
		}
	}

	/**
	 * doc
	 *
	 * @return TGMeasure 
	 */
	public TGMeasure getMeasure() {
		return this.measure;
	}

	/**
	 * doc
	 *
	 * @param TGMeasure
	 */
	public void setMeasure(TGMeasure measure) {
		this.measure = measure;
	}

	/**
	 * doc
	 *
	 * @return the start time of this beat
	 */
	public long getStart() {
		// prefer precise start if available
		if (this.preciseStart != null) {
			return TGDuration.toTime(this.preciseStart);
		}
		return this.start;
	}

	/**
	 * doc
	 * @return the precise start
	 */
	public Long getPreciseStart() {
		return this.preciseStart;
	}

	/**
	 * Deprecated: sets the approximate start time of this beat using legacy midi-tick resolution.
	 *
	 * Resolution of beat start equals one midi tick, which is not precise enough to handle
	 * all possible tuplets. Only use for legacy purposes (e.g. importing old or foreign file formats).
	 * When this method is called, make sure to update preciseStart afterwards using dedicated
	 * methods available in TGMeasureManager.
	 *
	 * @param start the approximate start time in midi ticks
	 * @deprecated replaced by {@link #setPreciseStart(long)}
	 */
	@Deprecated
	public void setStart(long start) {
		this.start = start;
		// cannot deduce preciseStart from start (possible rounding errors)
		this.preciseStart = null;
	}

	/**
	 * Doc
	 *
	 * @param pStart
	 */
	public void setPreciseStart(long pStart) {
		this.preciseStart = pStart;
		this.start = TGDuration.toTime(pStart);
	}

	/**
	 * doc
	 *
	 * @param index 
	 * @param voice
	 */
	public void setVoice(int index, TGVoice voice){
		if( index >= 0 && index < this.voices.length ){
			this.voices[index] = voice;
			this.voices[index].setBeat( this );
		}
	}

	/**
	 * Doc
	 *
	 * @param index 
	 * @return TGVoice or null
	 */
	public TGVoice getVoice(int index){
		if( index >= 0 && index < this.voices.length ){
			return this.voices[index];
		}
		return null;
	}

	/**
	 *  Doc
	 *
	 * @return the highest fret number
	 */
	public int getHighestFret() {
		int highestFret = -1;
		for (int i=0; i<this.countVoices(); i++) {
			int voiceHighestFret = this.getVoice(i).getHighestFret();
			highestFret = (voiceHighestFret > highestFret ? voiceHighestFret : highestFret);
		}
		return highestFret;
	}

	/**
	 * Doc
	 *
	 * @return the number of voices
	 */
	public int countVoices(){
		return this.voices.length;
	}

	/**
	 * Doc
	 *
	 * @param chord the TGChord 
	 */
	public void setChord(TGChord chord) {
		this.chord = chord;
		this.chord.setBeat(this);
	}

	/**
	 *  Doc
	 *
	 * @return the TGChord of this beat, or null
	 */
	public TGChord getChord() {
		return this.chord;
	}

	/**
	 *  Doc
	 */
	public void removeChord() {
		this.chord = null;
	}

	/**
	 *  Doc
	 *
	 * @return the TGText
	 */
	public TGText getText() {
		return this.text;
	}

	/**
	 *  Doc
	 *
	 * @param text the TGText
	 */
	public void setText(TGText text) {
		this.text = text;
		this.text.setBeat(this);
	}

	/**
	 *  Doc
	 */
	public void removeText(){
		this.text = null;
	}

	/**
	 *  Doc
	 *
	 * @return true else false
	 */
	public boolean isChordBeat(){
		return ( this.chord != null );
	}

	/**
	 *  Doc
	 *
	 * @return true else false
	 */
	public boolean isTextBeat(){
		return ( this.text != null );
	}

	/**
	 * R Doc
	 *
	 * @return the TGStroke
	 */
	public TGStroke getStroke() {
		return this.stroke;
	}

	/**
	 *  Doc
	 *
	 * @return the TGPickStroke
	 */
	public TGPickStroke getPickStroke() {
		return this.pickStroke;
	}

	/**
	 *  Doc
	 *
	 * @return true else false
	 */
	public boolean isPickStroke() {
		return (this.pickStroke.getDirection() != TGPickStroke.PICK_STROKE_NONE);
	}

	/**
	 *  Doc
	 *
	 * @return true else false
	 */
	public boolean isRestBeat(){
		for(int v = 0; v < this.countVoices() ; v ++ ){
			TGVoice voice = this.getVoice( v );
			if( !voice.isEmpty() && !voice.isRestVoice() ){
				return false;
			}
		}
		return true;
	}

	/**
	 *  Doc
	 */
	public void resetAltEnharmonic() {
		for(int v = 0; v < this.countVoices() ; v ++ ){
			TGVoice voice = this.getVoice( v );
				voice.resetAltEnharmonic();
		}
	}

	/** 
	 *  Doc
	 * @param beat
	 * @param factory
	 */
	public void copyFrom(TGBeat beat, TGFactory factory) {
		if (beat.getPreciseStart() != null) {
			this.setPreciseStart(beat.getPreciseStart());
		} else {
			this.setStart(beat.getStart());
		}
		this.getStroke().copyFrom(beat.getStroke());
		this.getPickStroke().copyFrom(beat.getPickStroke());
		for( int i = 0 ; i < beat.voices.length ; i ++ ){
			this.setVoice(i, beat.voices[i].clone(factory));
		}
		if(beat.chord != null){
			this.setChord( beat.chord.clone(factory));
		}
		if(beat.text != null){
			this.setText( beat.text.clone(factory));
		}
	}

	/**
	 *  Doc
	 *
	 * @param TGFactory
	 * @return a new TGBeat
	 */
	public TGBeat clone(TGFactory factory){
		TGBeat beat = factory.newBeat();
		beat.copyFrom(this, factory);
		return beat;
	}

	/**
	 *  Doc
	 *
	 * @param  TGBeat
	 * @return something
	 */
	@Override
	public int compareTo(TGBeat beat) {
		if (beat == null) return 1;
		if ((this.preciseStart != null) && (beat.getPreciseStart()!= null)) {
			return Long.valueOf(this.preciseStart).compareTo(Long.valueOf(beat.getPreciseStart()));
		}
		return (Long.valueOf(this.getStart()).compareTo(Long.valueOf(beat.getStart())));
	}
}