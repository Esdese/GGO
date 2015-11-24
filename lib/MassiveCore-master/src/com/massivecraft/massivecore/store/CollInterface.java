package com.massivecraft.massivecore.store;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.Named;
import com.massivecraft.massivecore.Predicate;
import com.massivecraft.massivecore.xlib.gson.JsonElement;

public interface CollInterface<E extends Entity<E>> extends Named
{
	// -------------------------------------------- //
	// WHAT DO WE HANDLE?
	// -------------------------------------------- //
	
	public String getName();
	public String getBasename();
	public String getUniverse();
	public Class<E> getEntityClass();
	
	// -------------------------------------------- //
	// SUPPORTING SYSTEM
	// -------------------------------------------- //
	
	public Plugin getPlugin();
	
	public Db getDb();
	public Object getCollDriverObject();
	
	public boolean supportsPusher();
	public PusherColl getPusher();
	
	// -------------------------------------------- //
	// STORAGE
	// -------------------------------------------- //
	
	public String fixId(Object oid);
	public String fixIdOrThrow(Object oid) throws IllegalArgumentException;
	
	public Map<String, E> getId2entity();
	public E get(Object oid);
	public E get(Object oid, boolean creative);
	public E getFixed(String id);
	public E getFixed(String id, boolean creative);
	public Collection<String> getIds();
	public Collection<String> getIdsRemote();
	public boolean containsId(Object oid);
	public boolean containsIdFixed(String id);
	
	public Map<E, String> getEntity2id();
	public String getId(Object entity);
	public boolean containsEntity(Object entity);
	
	public Collection<E> getAll();
	
	public List<E> getAll(Iterable<?> oids, Predicate<? super E> where, Comparator<? super E> orderby, Integer limit, Integer offset);
	public List<E> getAll(Iterable<?> oids, Predicate<? super E> where, Comparator<? super E> orderby, Integer limit);
	public List<E> getAll(Iterable<?> oids, Predicate<? super E> where, Comparator<? super E> orderby);
	public List<E> getAll(Iterable<?> oids, Predicate<? super E> where, Integer limit, Integer offset);
	public List<E> getAll(Iterable<?> oids, Predicate<? super E> where, Integer limit);
	public List<E> getAll(Iterable<?> oids, Comparator<? super E> orderby, Integer limit, Integer offset);
	public List<E> getAll(Iterable<?> oids, Comparator<? super E> orderby, Integer limit);
	public List<E> getAll(Iterable<?> oids, Predicate<? super E> where);
	public List<E> getAll(Iterable<?> oids, Comparator<? super E> orderby);
	public List<E> getAll(Iterable<?> oids, Integer limit, Integer offset);
	public List<E> getAll(Iterable<?> oids, Integer limit);
	public List<E> getAll(Iterable<?> oids);
	
	public List<E> getAll(Predicate<? super E> where, Comparator<? super E> orderby, Integer limit, Integer offset);
	public List<E> getAll(Predicate<? super E> where, Comparator<? super E> orderby, Integer limit);
	public List<E> getAll(Predicate<? super E> where, Comparator<? super E> orderby);
	public List<E> getAll(Predicate<? super E> where, Integer limit, Integer offset);
	public List<E> getAll(Predicate<? super E> where, Integer limit);
	public List<E> getAll(Comparator<? super E> orderby, Integer limit, Integer offset);
	public List<E> getAll(Comparator<? super E> orderby, Integer limit);
	public List<E> getAll(Predicate<? super E> where);
	public List<E> getAll(Comparator<? super E> orderby);
	public List<E> getAll(Integer limit, Integer offset);
	public List<E> getAll(Integer limit);
		
	// -------------------------------------------- //
	// BEHAVIOR
	// -------------------------------------------- //
	
	public boolean isCreative();
	public void setCreative(boolean creative);
	
	public boolean isLowercasing();
	public void setLowercasing(boolean lowercasing);
	
	public int getLocalPollInfrequency();
	public void setLocalPollInfrequency(int frequency);
	
	public boolean isWarningOnLocalAlter();
	public void setWarnOnLocalAlter(boolean warnOnLocalAlter);
	
	// A default entity will not be saved.
	// This is often used together with creative collections to save disc space.
	public boolean isDefault(E entity);
	
	// -------------------------------------------- //
	// COPY AND CREATE
	// -------------------------------------------- //
	
	public void copy(E fromo, E too);
	
	// This simply creates and returns a new instance
	// It does not detach/attach or anything. Just creates a new instance.
	public E createNewInstance();
	
	// Create new instance with automatic id
	public E create();
	// Create new instance with the requested id
	public E create(Object oid);
	
	// -------------------------------------------- //
	// ATTACH AND DETACH
	// -------------------------------------------- //
	
	public String attach(E entity);
	public String attach(E entity, Object oid);
	
	public E detachEntity(E entity);
	public E detachId(Object oid);
	public E detachIdFixed(String id);
	
	public void preAttach(E entity, String id);
	public void postAttach(E entity, String id);
	
	public void preDetach(E entity, String id);
	public void postDetach(E entity, String id);
	
	// -------------------------------------------- //
	// IDENTIFIED MODIFICATIONS
	// -------------------------------------------- //
	
	/*
	public Set<L> localAttachIds();
	public Set<L> localDetachIds();
	public Set<L> changedIds();
	public void clearIdentifiedChanges(Object oid);
	*/
	
	// -------------------------------------------- //
	// SYNC LOG
	// -------------------------------------------- //
	
	/*
	public Map<L, Long> lastMtime();
	public Map<L, Object> lastRaw();
	public Set<L> lastDefault();
	public void clearSynclog(Object oid);
	*/
	
	public Map<String, Long> getSyncMap(boolean in);
	public long getSyncCountFixed(String name, boolean in);
	public void addSyncCountFixed(String name, boolean in);
	
	// -------------------------------------------- //
	// SYNC LOWLEVEL IO ACTIONS
	// -------------------------------------------- //
	
	// oid
	public E removeAtLocal(Object oid);
	public void removeAtRemote(Object oid);
	public void saveToRemote(Object oid);
	public void loadFromRemote(Object oid, Entry<JsonElement, Long> remoteEntry);
	
	// Fixed id
	public E removeAtLocalFixed(String id);
	public void removeAtRemoteFixed(String id);
	public void saveToRemoteFixed(String id);
	public void loadFromRemoteFixed(String id, Entry<JsonElement, Long> remoteEntry);
	
	// -------------------------------------------- //
	// SYNC EXAMINE AND DO
	// -------------------------------------------- //
	
	// oid
	public Modification examineId(Object oid);
	public Modification examineId(Object oid, Long remoteMtime);
	public Modification examineIdLocal(Object oid);
	public Modification examineIdRemote(Object oid);
	public Modification examineIdRemote(Object oid, Long remoteMtime);
	
	// Fixed id
	public Modification examineIdFixed(String id);
	public Modification examineIdFixed(String id, Long remoteMtime);
	public Modification examineIdLocalFixed(String id);
	public Modification examineIdRemoteFixed(String id);
	public Modification examineIdRemoteFixed(String id, Long remoteMtime);
	
	// Sync
	public Modification syncId(Object oid);
	public Modification syncId(Object oid, Modification modification);
	public Modification syncId(Object oid, Modification modification, Entry<JsonElement, Long> remoteEntry);
	
	// Sync fixed
	public Modification syncIdFixed(String id);
	public Modification syncIdFixed(String id, Modification modification);
	public Modification syncIdFixed(String id, Modification modification, Entry<JsonElement, Long> remoteEntry);
	
	public void syncIdentified();
	public void syncAll();
	
	// Identity
	public void identifyModifications(boolean sure);
	public void identifyModificationFixed(String id, Long remoteMtime, boolean sure);
	
	public void identifyLocalModifications(boolean sure);
	public void identifyLocalModificationFixed(String id, boolean sure);
	
	public void identifyRemoteModifications(boolean sure);
	public void identifyRemoteModificationFixed(String id, Long remoteMtime, boolean sure);
	
	// Init
	public void initLoadAllFromRemote();
	
	// -------------------------------------------- //
	// SYNC RUNNABLES / SCHEDULING
	// -------------------------------------------- //
	
	// The tickTask simply runs the onTick method.
	public Runnable getTickTask();
	public void onTick();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public void init();
	public void deinit();
	public boolean inited();
	
}
