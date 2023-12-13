with open(file=r'Data', mode='r') as file:
    lines = file.read().splitlines()

blocks: list[list[str]] = [[]]
for line in lines:
    if len(line) == 0:
        blocks.append([])
        continue
    blocks[-1].append(line)

def are_smudged(line1: str, line2: str) -> bool:
    return [chr1 == chr2 for chr1, chr2 in zip(line1, line2)].count(False) == 1

result: int = 0
for block in blocks:
    rows = block
    cols = ["".join(t) for t in zip(*rows)]

    refl_row: int = -1
    refl_col: int = -1

    # Check rows
    for i in range(len(rows) - 1):
        top = i
        bottom = i + 1
        is_valid = True
        diffs = 1
        while 0 <= top and bottom < len(rows):
            if rows[top] != rows[bottom] and not are_smudged(rows[top], rows[bottom]):
                is_valid = False
                break
            if are_smudged(rows[top], rows[bottom]):
                diffs -= 1
            if diffs < 0:
                is_valid = False
                break
            top -= 1
            bottom += 1
        if is_valid and diffs == 0:
            refl_row = i + 1
            break
    # Check columns
    for i in range(len(cols) - 1):
        left = i
        right = i + 1
        is_valid = True
        diffs = 1
        while 0 <= left and right < len(cols):
            if cols[left] != cols[right] and not are_smudged(cols[left], cols[right]):
                is_valid = False
                break
            if are_smudged(cols[left], cols[right]):
                diffs -= 1
            if diffs < 0:
                is_valid = False
                break
            left -= 1
            right += 1
        if is_valid and diffs == 0:
            refl_col = i + 1
            break
    if refl_row != -1:
        result += 100 * refl_row
    if refl_col != -1:
        result += refl_col

print(result)