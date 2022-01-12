# Content of all the problems in html form.

## introduction
1. Fetch all the raw filles using *wget* </br>
<code> wget https://projecteuler.net/minimal={0001..0761} </code>   
(change 0761 to the latest problem on the site)

2. Change the names of all the filles using *rename*  </br>
<code> rename 's/minimal=/Problem_/' * </code>

3. Add the .html file extension using *mv*  </br>
<code>  for f in *; do mv "$f" "$f.md"; done </code> </br>
(note: because github won't display pure html it was converted to .md instead)

4. Upload it to github </br>

### Cool one liner, so you won't have to wait for each step to complete before executing the next one
wget https://projecteuler.net/minimal={0001..0761} && rename 's/minimal=/Problem_/' * && for f in *; do mv "$f" "$f.md"; done 
