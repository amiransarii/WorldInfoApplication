package com.example.worldinfoapplication.enitity;

/**
 * <code>ViewHolder</code> Cache interface for view <br><pre>
 * Used when speeding up the access by re-using the view.
 * To create a cache for the class of View in the following manner.
 * with <code>
 * static class ViewHolderImpl implements ViewHolder{
 *     TextView screenName;
 *
 *     TextView text;
 *
 *
 *
 *     ImageView image;
 * }
 * </code>
 *
 * Set the ViewHolder to new when creating the View for the first time, and set the reference of ImageView and TextView (child views) in holder
 * Registered it as a tag in view.setTag ().
 * Thus, when the reusable views is passed, as view.getTag (), it becomes possible to obtain the ViewHolder with a reference to the child views.
 * </pre>
 * @see AppBaseAdapter#createViewHolder(View)
 * @see AppBaseAdapter#getViewHolder(View)
 *
 */
public interface ViewHolder {
}
